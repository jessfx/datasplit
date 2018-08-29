package dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Part2_ParamCalMapper extends Mapper<LongWritable, Text, Text, Text> {

	int i=0;
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		context.write(new Text(), ivalue);
	}

}
