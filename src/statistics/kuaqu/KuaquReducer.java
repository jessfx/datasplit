package statistics.kuaqu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KuaquReducer extends Reducer<IntWritable, IntWritable, NullWritable, Text> {

	public void reduce(IntWritable _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		int i=0;
		for (IntWritable val : values) {
			i++;
		}
		String string=String.valueOf(i);
		context.write(NullWritable.get(), new Text(string));
	}

}
