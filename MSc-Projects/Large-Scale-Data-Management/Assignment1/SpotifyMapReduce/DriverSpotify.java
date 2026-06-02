package gr.aueb.panagiotisl.mapreduce.spotify;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Driver for Part II:
 * For each country-month key (e.g., "GR:2024-01") output:
 *   mostDanceableSong: <danceability>, avg: <averageDanceability>
 */
public class DriverSpotify {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Spotify Danceability per Country-Month");

        job.setJarByClass(DriverSpotify.class);

        // Mapper emits (Text, DanceAggWritable)
        job.setMapperClass(SpotifyDanceability.Map.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(SpotifyDanceability.DanceAggWritable.class);

        // Combiner is safe (sum/count/max are associative/commutative)
        job.setCombinerClass(SpotifyDanceability.Combine.class);

        // Reducer outputs (Text, Text) in the requested format
        job.setReducerClass(SpotifyDanceability.Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Input/Output paths (as required in the assignment)
        FileInputFormat.addInputPath(job, new Path("/user/hdfs/input/spotify.csv"));
        FileOutputFormat.setOutputPath(job, new Path("/user/hdfs/output_spotify/"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
