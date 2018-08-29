package datamerge;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.hdfs.protocol.proto.NamenodeProtocolProtos.StartCheckpointRequestProto;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import module.writable.JoinInfoWritable;

public class DateMergeReducer extends Reducer<Text, JoinInfoWritable, Text, JoinInfoWritable> {
	public static JoinInfoWritable start;
	public static JoinInfoWritable end;

	@Override
	public void reduce(Text _key, Iterable<JoinInfoWritable> values, Context context) throws IOException, InterruptedException {
		Iterator<JoinInfoWritable> iterable=values.iterator();
		JoinInfoWritable val=iterable.next();
		if(start!=null){
			if(val.getMac().compareTo(start.getMac())==0&&val.getDate().compareTo(start.getDate())==0&&val.getAddress().compareTo(start.getAddress())==0){
				end.set(val);
			}
			else {
				context.write(new Text(""), end);
				start.set(val);
				context.write(new Text(""), start);
				end.set(start);
			}
		}else{
			start=new JoinInfoWritable(val);
			context.write(new Text(""), start);
			System.out.println(start.toString());
			end=new JoinInfoWritable(start);
		}
		while(iterable.hasNext()){
			val=iterable.next();
			if(val.getMac().compareTo(start.getMac())==0&&val.getDate().compareTo(start.getDate())==0&&val.getAddress().compareTo(start.getAddress())==0){
				end.set(val);
			}
			else {
				context.write(new Text(""), end);
				start.set(val);
				context.write(new Text(""), start);
				end.set(start);
			}
		}
	}

}
