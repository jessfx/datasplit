package statistics.weekendANDnweekend;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DeviceMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		StringTokenizer stringTokenizer=new StringTokenizer(ivalue.toString(),"\n");
		while(stringTokenizer.hasMoreTokens()){
			context.write(new Text(""), new Text(stringTokenizer.nextToken()));
		}
	}

}
