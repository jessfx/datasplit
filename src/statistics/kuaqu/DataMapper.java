package statistics.kuaqu;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import module.writable.DeviceInfoWritable;
import module.writable.InfoWritable;

public class DataMapper extends Mapper<LongWritable, Text, Text, ObjectWritable> {
	
	public DeviceInfoWritable infoWritable=new DeviceInfoWritable();
	public Text text=new Text();
	public ObjectWritable objectWritable=new ObjectWritable();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings=ivalue.toString().split("\\s+");
		infoWritable.setMac(strings[1]);
		infoWritable.setDate(strings[2]);
		infoWritable.setTime(strings[3]);
		infoWritable.setAddress(strings[5]);
		text.set(strings[4].replace("\\r\\n", ""));
		objectWritable.set(infoWritable);
//		System.out.println(strings[1]);
//		System.out.println(strings[2]);
//		System.out.println(strings[3]);
//		System.out.println(strings[5]);
		context.write(text, objectWritable);
	}

}
