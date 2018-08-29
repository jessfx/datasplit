package statistics.weekendANDnweekend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import module.Location;

public class DeviceDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		String dict="mastertable";
		Path inputpath=new Path("hdfs://master:9000/data/"+dict);
		Path outputpath=new Path("hdfs://master:9000/output/"+"statistics A,rc");
		FileSystem fs=FileSystem.get(new URI("hdfs://master:9000"), conf);
		if(fs.exists(outputpath)==true){
			fs.delete(outputpath);
		}
		job.setJarByClass(statistics.weekendANDnweekend.DeviceDriver.class);
		job.setMapperClass(statistics.weekendANDnweekend.DeviceMapper.class);

		job.setReducerClass(DeviceReducer11.class);
		if(job.getReducerClass()==DeviceReducer12.class||job.getReducerClass()==DeviceReducer02.class){
			loadLocation();
		}

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(1);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, inputpath);
		FileOutputFormat.setOutputPath(job, outputpath);

		if (!job.waitForCompletion(true))
			return;
	}
	
	public static void loadLocation(){
		try(BufferedReader bufferedReader=new BufferedReader(new FileReader("D:\\gpssuse.txt"));) {
			String tempString;
			while((tempString=bufferedReader.readLine())!=null){
				Location.location.put(tempString, tempString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
