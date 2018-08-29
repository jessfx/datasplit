package dbscan;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class StatisticFinallyReducer extends Reducer<IntWritable, Text, NullWritable, Text> {
	
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

	public void reduce(IntWritable _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int sum=0;
		for (Text val : values) {
			sum+=Integer.parseInt(val.toString());
		}
		mos.write(NullWritable.get(), new Text(String.valueOf(sum)), "cluster");
	}

}
