package tablejoin;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import module.GpsInfo;

public class GpsMapper extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		StringTokenizer str=new StringTokenizer(ivalue.toString(), "\n");
//		System.out.println("a");
		while(str.hasMoreTokens()){
			String[] strings=str.nextToken().split("\t");
			GpsInfo gpsInfo=new GpsInfo(strings[0],strings[2],strings[3]);
			Main.hashMap.put(strings[0], gpsInfo);
			Main.hashMap1.put(strings[3], gpsInfo);
		}
	}

}
