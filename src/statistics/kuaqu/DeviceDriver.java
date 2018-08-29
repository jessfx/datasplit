package statistics.kuaqu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import module.Location;
import module.writable.DeviceInfoWritable;

public class DeviceDriver {

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "tablejoin");
		Path inputpath1=new Path("hdfs://master:9000/output/"+"addressmerge");
		Path inputpath2=new Path("hdfs://master:9000/data/"+"result");
		Path outputpath=new Path("hdfs://master:9000/output/"+"kuaqu,tablejoin");
		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
		
//		if(fs.exists(outputpath)==true){
//			fs.delete(outputpath);
//		}
//		job.setJarByClass(DeviceDriver.class);
//		job.setReducerClass(JoinReducer.class);
//
//		MultipleInputs.addInputPath(job, inputpath1, TextInputFormat.class, DataMapper.class);
//		MultipleInputs.addInputPath(job, inputpath2, TextInputFormat.class,ResultMapper.class);
//		// TODO: specify output types
//		job.setOutputKeyClass(NullWritable.class);
//		job.setOutputValueClass(Text.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(ObjectWritable.class);
//
//		// TODO: specify input and output DIRECTORIES (not files)
//		
//		FileOutputFormat.setOutputPath(job, outputpath);
//		job.waitForCompletion(true);
		

//		String dict="addressmerge";
		Path inputpath=new Path("hdfs://master:9000/output/"+"kuaqu,tablejoin");
		outputpath=new Path("hdfs://master:9000/output/"+"kuaqu,tablejoinsort");
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job = Job.getInstance(conf, "kuaqu tablejoin sort");
		job.setJarByClass(DeviceDriver.class);
		job.setMapperClass(TableJoinSortMapper.class);
		job.setReducerClass(TableJoinSortReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setNumReduceTasks(1);
		// TODO: specify output types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, inputpath);
		FileOutputFormat.setOutputPath(job, outputpath);
		job.waitForCompletion(true);
		
		inputpath=outputpath;
		outputpath=new Path("hdfs://master:9000/output/"+"kuaqu,zhongjianbiao");
		job = Job.getInstance(conf, "kuaqu middle");
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job.setJarByClass(DeviceDriver.class);
		job.setMapperClass(DeviceMapperkuaqu.class);
		job.setReducerClass(DeviceReducerkuaqu.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DeviceInfoWritable.class);

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(1);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, inputpath);
		FileOutputFormat.setOutputPath(job, outputpath);
		job.waitForCompletion(true);


		inputpath=outputpath;
		outputpath=new Path("hdfs://master:9000/output/"+"kuaqu result");
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job = Job.getInstance(conf, "kuaqu result");
		job.setJarByClass(DeviceDriver.class);
		job.setMapperClass(KuaquMapper.class);
		job.setReducerClass(KuaquReducer.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);

		// TODO: specify output types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, inputpath);
		FileOutputFormat.setOutputPath(job, outputpath);
		job.waitForCompletion(true);
		
		
	}
	
	public static void loadLocation(){
		try(BufferedReader bufferedReader=new BufferedReader(new FileReader("/gps/gpssuse.txt"));) {
			String tempString;
			while((tempString=bufferedReader.readLine())!=null){
				Location.location.put(tempString, tempString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
