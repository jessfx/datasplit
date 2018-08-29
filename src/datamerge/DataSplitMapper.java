package datamerge;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import module.writable.JoinInfoWritable;

public class DataSplitMapper extends Mapper<LongWritable, Text, Text, JoinInfoWritable> {
	JoinInfoWritable info=new JoinInfoWritable();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings = ivalue.toString().split("\\s+");
//		System.out.println(ivalue.toString());
		info.set(strings[1], strings[2], strings[0], strings[3], strings[4]);
		context.write(new Text(info.getMac()+info.getDate()+info.getTime()), info);
	}

}
