package statistics.kuaqu;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TableJoinSortMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings=ivalue.toString().split("\\s+");
		context.write(new Text(strings[0]+" "+strings[1]+" "+strings[2]), ivalue);
	}

}
