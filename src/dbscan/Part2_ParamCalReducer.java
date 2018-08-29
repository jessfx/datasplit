package dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class Part2_ParamCalReducer extends Reducer<Text, Text, NullWritable, Text> {

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Part2_ParameterCal parameterCal=new Part2_ParameterCal();
		for (Text val : values) {
			parameterCal.execute(val.toString());
		}
		parameterCal.calMinPts();
		context.write(NullWritable.get(), new Text("?"+parameterCal.getEps()+" "+parameterCal.getMinPts()));
//		for(String data:dataList){
//			context.write(NullWritable.get(), new Text(data));
//		}
	}

}
