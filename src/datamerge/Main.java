package datamerge;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import module.writable.JoinInfoWritable;

public class Main {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		String dict="mastertable";
		Path inputpath=new Path("hdfs://master:9000/data/"+dict);
		Path outputpath=new Path("hdfs://master:9000/output/"+"addressmerge");
		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job.setJarByClass(datamerge.Main.class);
		job.setMapperClass(DataSplitMapper.class);
		job.setReducerClass(AddressMergeReduce.class);
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(JoinInfoWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(JoinInfoWritable.class);

		FileInputFormat.setInputPaths(job, inputpath);
		FileOutputFormat.setOutputPath(job, outputpath);

		if (!job.waitForCompletion(true))
			return;
	}

}
