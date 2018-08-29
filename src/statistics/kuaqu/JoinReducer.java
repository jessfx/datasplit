package statistics.kuaqu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import module.writable.DeviceInfoWritable;
import module.writable.InfoWritable;

public class JoinReducer extends Reducer<Text, ObjectWritable, NullWritable, Text> {
	
	
	
	Text text2=new Text();

	public void reduce(Text _key, Iterable<ObjectWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		String text=null;
//		System.out.println(_key.toString());
		List<DeviceInfoWritable> list=new ArrayList<DeviceInfoWritable>();
		for (ObjectWritable val : values) {
			if(val.get() instanceof DeviceInfoWritable){
				DeviceInfoWritable deviceInfoWritable=(DeviceInfoWritable)val.get();
				list.add(deviceInfoWritable);
			}else {
				text=((Text)val.get()).toString();
			}
		}
		if(text!=null){
			for (DeviceInfoWritable infoWritable : list) {
				infoWritable.setAddress(text);
				text2.set(infoWritable.getMac()+" "+infoWritable.getDate()+" "+infoWritable.getTime()+" "+infoWritable.getAddress());
				context.write(NullWritable.get(), text2);
			}
		}
	}

}
