package tablejoin;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import module.GpsInfo;
import module.writable.InfoWritable;
import module.writable.JoinInfoWritable;
import util.RegexMatches;

public class TableJoinMapper extends Mapper<LongWritable, Text, Text, JoinInfoWritable> {

	@Override
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		StringTokenizer str=new StringTokenizer(ivalue.toString(), "\n");
		String ap="";
		String mac="";
		while(str.hasMoreTokens()){
			String[] string=str.nextToken().split("\t");
			String[] result=RegexMatches.findData(string[5]);
			ap=result[0];
			mac=result[1];
//			System.out.println(ap);
			InfoWritable infoWritable=new InfoWritable(string[2], string[3], ap,mac);
			if(Main.hashMap.containsKey(infoWritable.getAp())){
				GpsInfo gpsInfo=Main.hashMap.get(infoWritable.getAp());
				JoinInfoWritable joinInfoWritable=new JoinInfoWritable(infoWritable, gpsInfo);
				context.write(new Text(joinInfoWritable.getMac()+joinInfoWritable.getDate()+joinInfoWritable.getTime()), joinInfoWritable);
			}
		}
	}

}
