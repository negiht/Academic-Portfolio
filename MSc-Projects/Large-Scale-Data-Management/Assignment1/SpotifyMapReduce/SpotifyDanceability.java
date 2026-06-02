package gr.aueb.panagiotisl.mapreduce.spotify;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Part II MapReduce logic.
 *
 * Key:   "CC:YYYY-MM" (e.g., "GR:2024-01")
 * Value (internal): carries partial aggregates so we can use a combiner:
 *   - sumDanceability
 *   - count
 *   - maxDanceability + maxSong
 *
 * Final reducer prints:
 *   <maxSong>: <maxDanceability>, avg: <avgDanceability>
 */
public class SpotifyDanceability {

    /** Custom Writable for partial aggregation (enables combiner). */
    public static class DanceAggWritable implements Writable {
        private FloatWritable sum = new FloatWritable(0f);
        private IntWritable count = new IntWritable(0);
        private FloatWritable max = new FloatWritable(Float.NEGATIVE_INFINITY);
        private Text maxSong = new Text("");

        public DanceAggWritable() {}

        public static DanceAggWritable fromSingleSong(String song, float danceability) {
            DanceAggWritable w = new DanceAggWritable();
            w.sum.set(danceability);
            w.count.set(1);
            w.max.set(danceability);
            w.maxSong.set(song);
            return w;
        }

        public void merge(DanceAggWritable other) {
            // Sum + Count (for average)
            this.sum.set(this.sum.get() + other.sum.get());
            this.count.set(this.count.get() + other.count.get());

            // Max (for most danceable song)
            if (other.max.get() > this.max.get()) {
                this.max.set(other.max.get());
                this.maxSong.set(other.maxSong.toString());
            }
        }

        public float getSum() { return sum.get(); }
        public int getCount() { return count.get(); }
        public float getMax() { return max.get(); }
        public String getMaxSong() { return maxSong.toString(); }

        @Override
        public void write(DataOutput out) throws IOException {
            sum.write(out);
            count.write(out);
            max.write(out);
            maxSong.write(out);
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            sum.readFields(in);
            count.readFields(in);
            max.readFields(in);
            maxSong.readFields(in);
        }
    }

    /** Mapper: parses CSV line, emits key=country:YYYY-MM, value=single-song aggregate. */
    public static class Map extends Mapper<LongWritable, Text, Text, DanceAggWritable> {

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            // Ignore header (assignment requirement)
            if (line.startsWith("spotify_id,")) return;

            // CSV-safe split (assignment provides this approach)
            String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // We need at least tokens up to danceability column.
            // In this dataset: song name is [1], country is [6], snapshot_date is [7], danceability is [13].
            if (tokens.length <= 13) return;

            try {
                String song = unquote(tokens[1]).trim();
                String country = unquote(tokens[6]).trim();
                String snapshotDate = unquote(tokens[7]).trim(); // "YYYY-MM-DD"
                String danceRaw = unquote(tokens[13]).trim();

                if (song.isEmpty() || country.isEmpty() || snapshotDate.length() < 7 || danceRaw.isEmpty()) return;

                String month = snapshotDate.substring(0, 7); // YYYY-MM
                float dance = Float.parseFloat(danceRaw);

                Text outKey = new Text(country + ":" + month);
                DanceAggWritable outVal = DanceAggWritable.fromSingleSong(song, dance);

                context.write(outKey, outVal);

            } catch (Exception e) {
                // Bad line -> ignore
            }
        }

        private String unquote(String s) {
            if (s == null) return "";
            s = s.trim();
            if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
                return s.substring(1, s.length() - 1);
            }
            return s;
        }
    }

    /** Combiner: merges partial aggregates for the same key. */
    public static class Combine extends Reducer<Text, DanceAggWritable, Text, DanceAggWritable> {
        @Override
        public void reduce(Text key, Iterable<DanceAggWritable> values, Context context)
                throws IOException, InterruptedException {

            DanceAggWritable agg = new DanceAggWritable();
            for (DanceAggWritable v : values) {
                agg.merge(v);
            }
            context.write(key, agg);
        }
    }

    /** Reducer: final merge + format output string exactly as requested. */
    public static class Reduce extends Reducer<Text, DanceAggWritable, Text, Text> {
        @Override
        public void reduce(Text key, Iterable<DanceAggWritable> values, Context context)
                throws IOException, InterruptedException {

            DanceAggWritable agg = new DanceAggWritable();
            for (DanceAggWritable v : values) {
                agg.merge(v);
            }

            float avg = (agg.getCount() == 0) ? 0f : (agg.getSum() / agg.getCount());

            // Format: "Song: 0.922, avg: 0.454"
            String out = String.format("%s: %.3f, avg: %.3f",
                    agg.getMaxSong(), agg.getMax(), avg);

            context.write(key, new Text(out));
        }
    }
}
