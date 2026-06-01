import pandas as pd
import plotly.express as px
import argparse

parser = argparse.ArgumentParser()
# Prepare a parser to accept two arguments:
# 1. Input csv filename
# 2. The title of the chart (displayed above the chart area)
parser.add_argument('filename', type=str, help="Define the csv filename")
parser.add_argument('title', type=str, help="Define the chart tile")

# Create a list of issued arguments
args = vars(parser.parse_args())

csvfile = args['filename']
chartTitle = args['title']

print ("Loading " + csvfile + "...")

# Read and parse input csv file
df = pd.read_csv(csvfile)
noOfColumns = len(df.columns)

print ("Preparing chart " + chartTitle)

# Create a dot scattered chart
if noOfColumns == 2:
    fig = px.scatter(df, x = df.columns[0], y = df.columns[1], title=chartTitle)
if noOfColumns == 3:
    fig = px.scatter_3d(df, x = df.columns[0], y = df.columns[1], z = df.columns[2], title=chartTitle)

# Display the chart in the default output stream
fig.show()
