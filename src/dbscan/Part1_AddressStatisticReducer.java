package dbscan;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class Part1_AddressStatisticReducer extends Reducer<Text, Text, NullWritable, Text> {
	
	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int size=0;
//		System.out.println(this.toString());
		for (Text val : values) {
			String[] strings=val.toString().split(":");
			if(strings[0].compareTo("18")>=0&&strings[0].compareTo("24")<0)
				size++;
		}
		context.write(NullWritable.get(), new Text(_key.toString()+","+size));
	}

}
