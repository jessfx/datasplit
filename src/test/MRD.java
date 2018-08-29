package test;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MRD {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
		fs.delete(new Path("hdfs://master:9000/output/test"));
		job.setJarByClass(MRD.class);
		// TODO: specify a mapper
		job.setMapperClass(M.class);
		// TODO: specify a reducer
//		job.setReducerClass(R.class);
		job.setNumReduceTasks(0);

		// TODO: specify output types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
		job.setInputFormatClass(TextInputFormat.class);
		FileInputFormat.setMaxInputSplitSize(job, 256);
		
		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("hdfs://master:9000/data/compus"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://master:9000/output/test"));

		if (!job.waitForCompletion(true))
			return;
	}

}
