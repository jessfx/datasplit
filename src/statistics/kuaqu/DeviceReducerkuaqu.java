package statistics.kuaqu;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import module.writable.DeviceInfoWritable;

public class DeviceReducerkuaqu extends Reducer<Text, DeviceInfoWritable, Text, Text> {

	public DeviceInfoWritable infoWritable=null;
	public String address=null;
	
	
	public void reduce(Text _key, Iterable<DeviceInfoWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		for (DeviceInfoWritable val : values) {
			if(infoWritable==null){
				infoWritable=new DeviceInfoWritable(val);
				address=infoWritable.getAddress();
			}
			else if(val.getMac().compareTo(infoWritable.getMac())==0&&val.getDate().compareTo(infoWritable.getDate())!=0){
				context.write(new Text(), new Text(address+","+infoWritable.getAddress()));
				address=infoWritable.getAddress();
				infoWritable=new DeviceInfoWritable(val);
			}
			else{
				context.write(new Text(), new Text(address+","+infoWritable.getAddress()));
				address=val.getAddress();
				infoWritable=new DeviceInfoWritable(val);
			}
		}
	}

}
