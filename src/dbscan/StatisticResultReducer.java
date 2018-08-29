package dbscan;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class StatisticResultReducer extends Reducer<Text, Text, NullWritable, Text> {
	
	MultipleOutputs<NullWritable, Text> mos;
	
	@Override
	public void setup(Context context) throws IOException, InterruptedException{
		super.setup(context);
		mos=new MultipleOutputs<>(context);
	}
	
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException{
		super.cleanup(context);
		mos.close();
	}

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		String cluster=null,data=null;
		int i=0;
		System.out.println(this.toString());
		System.out.println(_key.toString());
		for (Text text : values) {
			if(text.toString().indexOf(",")!=-1){
				data=text.toString();
			}else{
				cluster=text.toString();
//				System.out.println(cluster);
				i++;
			}
		}
		System.out.println(i);
		if(data!=null&&cluster!=null){
			mos.write(NullWritable.get(), new Text(data+","+cluster), cluster);
			mos.write(NullWritable.get(), new Text(data+","+cluster.replace("cluster", "")), "cluster");
		}
	}

}
