package statistics.kuaqu;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.sun.corba.se.spi.orb.StringPair;

public class KuaquMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
	
	public static IntWritable intWritable=new IntWritable(1);

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		StringTokenizer stringTokenizer=new StringTokenizer(ivalue.toString(),"\n");
		while(stringTokenizer.hasMoreTokens()){
			String[] strings=stringTokenizer.nextToken().replaceAll("cluster", "").split(",");
			int i=Integer.parseInt(strings[0].trim())*100+Integer.parseInt(strings[1].trim());
			context.write(new IntWritable(i), intWritable);
		}
	}

}
