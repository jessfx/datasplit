package dbscan;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import module.writable.MetaDataWritable;

public class Part1_AddressStatisticMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings=ivalue.toString().split("\\s+");
		context.write(new Text(strings[4].replace("\\r\\n", "")), new Text(strings[3]));
	}

}
