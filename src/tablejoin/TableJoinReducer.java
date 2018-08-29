package tablejoin;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import module.GpsInfo;
import module.writable.JoinInfoWritable;

public class TableJoinReducer extends Reducer<Text, JoinInfoWritable, Text, JoinInfoWritable> {
	
	

	@Override
	public void reduce(Text _key, Iterable<JoinInfoWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		String mac="";
		for (JoinInfoWritable val : values) {
			if(Main.hashMap1.containsKey(val.getAddress())){
				GpsInfo gpsInfo=Main.hashMap1.get(val.getAddress());
				val.setGps(gpsInfo.getGps());
				mac=val.getMac();
				val.setMac("");
				context.write(new Text(mac), val);
			}
		}
	}

}
