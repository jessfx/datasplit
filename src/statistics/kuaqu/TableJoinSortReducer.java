package statistics.kuaqu;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableJoinSortReducer extends Reducer<Text, Text, NullWritable, Text> {

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		for (Text val : values) {
			String[] strings=val.toString().split("\\s+")[2].split(":");
			if(strings[0].compareTo("18")>=0&&strings[0].compareTo("24")<0)
				context.write(NullWritable.get(), val);
		}
	}

}
