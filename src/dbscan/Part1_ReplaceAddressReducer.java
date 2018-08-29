package dbscan;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class Part1_ReplaceAddressReducer extends Reducer<Text, Text, NullWritable, Text> {

	public String compus=null;
	private MultipleOutputs<NullWritable, Text> mos;
	
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
		compus=null;
		ArrayList<String[]> arrayList=new ArrayList<>();
		for (Text val : values) {
			String string=val.toString();
			if(string.charAt(0)=='#'){
				compus=string.replace("#", "");
			}
			else{
				string=string.replace("$", "");
				String[] strings=string.split("\\s+");
				arrayList.add(strings);
			}
		}
		for(String[] strings:arrayList){
			mos.write(NullWritable.get(), new Text(compus+" "+strings[4].replace("\\r\\n", "")),compus);
		}
	}

}
