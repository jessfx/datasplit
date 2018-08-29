package dbscan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.nustaq.serialization.util.test;

import module.writable.DeviceInfoWritable;
import module.writable.MetaDataWritable;
import statistics.kuaqu.DeviceDriver;
import statistics.kuaqu.DeviceMapperkuaqu;
import statistics.kuaqu.DeviceReducerkuaqu;

public class Driver {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		loadSchoolCompus();
		Configuration conf = new Configuration();
//		Job job = Job.getInstance(conf, "dbscan address replaceTo compus");
//		String dict="data20170412";
//		String compus="compus";
//		Path compuspath=new Path("hdfs://master:9000/data/"+compus);
//		Path datapath=new Path("hdfs://master:9000/data/"+dict);
//		Path outputpath=new Path("hdfs://master:9000/output/"+"ReplaceAddress");
//		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setReducerClass(Part1_ReplaceAddressReducer.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
//		job.setOutputKeyClass(NullWritable.class);
//		job.setOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//		MultipleInputs.addInputPath(job, compuspath, TextInputFormat.class,Part1_GpsMapper.class);
//		MultipleInputs.addInputPath(job, datapath, TextInputFormat.class,Part1_ReplaceAddressMapper.class);
//		FileInputFormat.setMaxInputSplitSize(job, 204800000);
//		FileInputFormat.setMinInputSplitSize(job, 1);
//		FileOutputFormat.setOutputPath(job, outputpath);
//		job.waitForCompletion(true);
		
		

		Job job = Job.getInstance(conf, "dbscan address count");
		String dict="addressmerge";
		Path datapath=new Path("hdfs://master:9000/output/"+dict);
		Path outputpath=new Path("hdfs://master:9000/output/"+"AddressCount");
		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
		
//		DataPartitionDriver.main(args);
//		
//		fs.copyFromLocalFile(new Path("output.txt"), new Path("hdfs://master:9000/data/gps"));
		
		
		job=Job.getInstance(conf,"dbscan parameterCal");
		datapath=new Path("hdfs://master:9000/data/DBSCAN");
		outputpath=new Path("hdfs://master:9000/output/"+"ParameterCal");
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job.setJarByClass(Driver.class);
		job.setMapperClass(Part2_ParamCalMapper.class);
		job.setReducerClass(Part2_ParamCalReducer.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, datapath);
		FileOutputFormat.setOutputPath(job, outputpath);
		job.waitForCompletion(true);
//		
//		
//        job = Job.getInstance(conf, "Dbscan");
//        // Set job name to locate it in the distributed environment
//        job.setJarByClass(Driver.class);
//
//        // Set input and output Path, note that we use the default input format
//        Path inputpath=new Path("hdfs://master:9000/data/DBSCAN");
//		outputpath=new Path("hdfs://master:9000/data/result");
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//        FileInputFormat.addInputPath(job, inputpath);
//        FileOutputFormat.setOutputPath(job, outputpath);
//
//        // Set Mapper and Reducer class
//        job.setMapperClass(DbscanMapper.class);
//        job.setReducerClass(DbscanReducer.class);
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(Text.class);
//        job.setInputFormatClass(TextInputFormat.class);
//	    job.setOutputFormatClass(TextOutputFormat.class);
//	    LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//        // Set Output key and value
//        job.setOutputKeyClass(NullWritable.class);
//        job.setOutputValueClass(Text.class);
//        
//        long start = new Date().getTime();
//        boolean status = job.waitForCompletion(true);            
//        long end = new Date().getTime();
//        System.out.println("Job took "+(end-start) + "milliseconds");
//11111111
//		job = Job.getInstance(conf, "dbscan address count");
//		dict="addressmerge";
//		datapath=new Path("hdfs://master:9000/output/"+dict);
//		outputpath=new Path("hdfs://master:9000/output/"+"AddressCount");
//		fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setMapperClass(Part1_AddressStatisticMapper.class);
//		job.setReducerClass(Part1_AddressStatisticReducer.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
//		job.setOutputKeyClass(NullWritable.class);
//		job.setOutputValueClass(Text.class);
//		FileInputFormat.setMaxInputSplitSize(job, 204800000);
//		FileInputFormat.setMinInputSplitSize(job, 1);
//		FileInputFormat.setInputPaths(job, datapath);
//		FileOutputFormat.setOutputPath(job, outputpath);
//		job.waitForCompletion(true);
//		
//		
//		job=Job.getInstance(conf,"statistic result tablejoin");
//		Path statisticspath=new Path("hdfs://master:9000/output/"+"AddressCount");
//		Path resultspath=new Path("hdfs://master:9000/data/"+"result");
//		outputpath=new Path("hdfs://master:9000/output/"+"ResultTableJoin");
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setReducerClass(StatisticResultReducer.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
//		job.setOutputKeyClass(NullWritable.class);
//		job.setOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//		MultipleInputs.addInputPath(job, statisticspath, TextInputFormat.class,StatisticMapper.class);
//		MultipleInputs.addInputPath(job, resultspath, TextInputFormat.class,ResultMapper.class);
//		FileInputFormat.setMaxInputSplitSize(job, 204800000);
//		FileInputFormat.setMinInputSplitSize(job, 1);
//		FileOutputFormat.setOutputPath(job, outputpath);
//		job.waitForCompletion(true);
//		
//
//		job=Job.getInstance(conf,"statistic");
//		outputpath=new Path("hdfs://master:9000/output/"+"Statistic");
//		Path inputPath=new Path("hdfs://master:9000/output/"+"ResultTableJoin");
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setMapOutputKeyClass(IntWritable.class);
//		job.setMapOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//		job.setMapperClass(StatisticFinallyMapper.class);
//		job.setReducerClass(StatisticFinallyReducer.class);
//
//		job.setOutputKeyClass(NullWritable.class);
//		job.setOutputValueClass(Text.class);
//
//		FileInputFormat.setInputPaths(job, inputPath);
//		FileOutputFormat.setOutputPath(job, outputpath);
//
//		job.waitForCompletion(true);
	//1111111	
		
//		job=Job.getInstance(conf,"dbscan clusting part1");
//		dict="ParameterCal";
//		datapath=new Path("hdfs://master:9000/output/"+dict);
//		outputpath=new Path("hdfs://master:9000/output/"+"DBSCAN result");
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setMapperClass(Part2_ParamCalMapper.class);
//		job.setReducerClass(Part2_ParamCalReducer.class);
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(Text.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//		FileInputFormat.setInputPaths(job, datapath);
//		FileOutputFormat.setOutputPath(job, outputpath);
//		FileInputFormat.setMaxInputSplitSize(job, 204800000);
//		FileInputFormat.setMinInputSplitSize(job, 1);
//		job.waitForCompletion(true);
//		
//		
//		job=Job.getInstance(conf,"dbscan clusting part2");
//		dict="ParameterCal";
//		datapath=new Path("hdfs://master:9000/output/"+dict);
//		outputpath=new Path("hdfs://master:9000/output/"+"DBSCAN result");
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(Driver.class);
//		job.setMapperClass(Part2_ParamCalMapper.class);
//		job.setReducerClass(Part2_ParamCalReducer.class);
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(Text.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
//		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
//		FileInputFormat.setInputPaths(job, datapath);
//		FileOutputFormat.setOutputPath(job, outputpath);
//		FileInputFormat.setMaxInputSplitSize(job, 204800000);
//		FileInputFormat.setMinInputSplitSize(job, 1);
//		job.waitForCompletion(true);
	}
	
	public static void loadSchoolCompus(){
		List<HashMap<String, String>> maps=new ArrayList<HashMap<String,String>>();
		maps.add(DeviceMapperkuaqu.huashan);
		maps.add(DeviceMapperkuaqu.taishan);
		maps.add(DeviceMapperkuaqu.qilin);
		maps.add(DeviceMapperkuaqu.yanshan);
		maps.add(DeviceMapperkuaqu.zuxiaoqu);
		String[] strings={"huashan","taishan","qilin","yanshan","zuxiaoqu"};
		String[] strings1={"华山区","泰山区","启林区","六一区","主校区"};
		for(int i=0;i<5;i++){
			try(BufferedReader bufferedReader=new BufferedReader(new FileReader("/gps/"+strings[i]+".txt"));){
				String temp;
				while((temp=bufferedReader.readLine())!=null){
					maps.get(i).put(temp, strings1[i]);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
