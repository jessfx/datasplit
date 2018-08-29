package datamerge;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import module.writable.JoinInfoWritable;

public class AddressMergeReduce extends Reducer<Text, JoinInfoWritable, Text, JoinInfoWritable> {
	public static JoinInfoWritable address;

	public void reduce(Text _key, Iterable<JoinInfoWritable> values, Context context) throws IOException, InterruptedException {
		for (JoinInfoWritable val : values) {
			if(address==null){
				address=new JoinInfoWritable(val);
			}
			else{
				if(val.getMac().compareTo(address.getMac())!=0||val.getDate().compareTo(address.getDate())!=0||val.getAddress().compareTo(address.getAddress())!=0){
					context.write(new Text(""), address);
					address=new JoinInfoWritable(val);
				}
			}
		}
	}

}
