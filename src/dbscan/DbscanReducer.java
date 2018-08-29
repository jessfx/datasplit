package dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class DbscanReducer extends Reducer<Text, Text, NullWritable, Text> {
	/*
	 * @ Param e eps
	 * @ Param minp minpts
	 * @ Param index cluster
	 */
	double e = 0.005;
	int minp = 3;
	Text word;
	int index = 1;
	
	MultipleOutputs<NullWritable, Text> mos;
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.setup(context);
		mos=new MultipleOutputs<>(context);
	}
	
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
		mos.close();
	}

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		/*
		 * @ Param all eps
		 * @ Param minp minpts
		 * @ Param index cluster
		 */
		
		for (Text text : values) {
			mos.write(NullWritable.get(), text, key.toString());
		}
	}
}