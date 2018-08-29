package dbscan;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatisticFinallyMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings=ivalue.toString().split(",");
//		System.out.println(strings[3]);
		context.write(new IntWritable(Integer.parseInt(strings[3].replace("cluster",""))), new Text(strings[2]));
	}

}
