package statistics.kuaqu;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import module.writable.DeviceInfoWritable;

public class DeviceMapperkuaqu extends Mapper<LongWritable, Text, Text, DeviceInfoWritable> {

	public static HashMap<String, String> huashan=new HashMap<>();
	public static HashMap<String, String> taishan=new HashMap<>();
	public static HashMap<String, String> qilin=new HashMap<>();
	public static HashMap<String, String> yanshan=new HashMap<>();
	public static HashMap<String, String> zuxiaoqu=new HashMap<>();
	public static DeviceInfoWritable info;

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		info=new DeviceInfoWritable();
		String[] strings=ivalue.toString().split("\\s+");
		info.setMac(strings[0]);
		info.setDate(strings[1]);
		info.setTime(strings[2]);
		info.setAddress(strings[3]);
		context.write(new Text(info.getMac()+info.getDate()+info.getTime()), info);
	}

}
