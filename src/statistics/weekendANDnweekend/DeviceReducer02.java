package statistics.weekendANDnweekend;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import module.Location;
import util.DateUtil;
/*
 * 周末：非周末，宿舍区，人数
 */
public class DeviceReducer02 extends Reducer<Text, Text, Text, Text> {

	public static HashMap<String, String> weekend=new HashMap<String, String>();
	public static HashMap<String, String> nweekend=new HashMap<String, String>();

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		HashMap<String, String> map = null;
		for (Text val : values) {
			String[] strings=val.toString().split("\\s+");
			DateUtil dateUtil=new DateUtil("yyyy.MM.dd");
			int week = -1;
			try {
				week = dateUtil.dateToWeekday(strings[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(week!=0&&week!=6){
				map=nweekend;
			}
			else if(week>0){
				map=weekend;
			}
			if(Location.location.containsKey(strings[strings.length-1])&&!map.containsKey(strings[0])){
				map.put(strings[0], strings[0]);
			}
		}
		context.write(new Text("weekend"), new Text("nweekend  D,rs"));
		context.write(new Text(String.valueOf((weekend.size()))), new Text(String.valueOf((nweekend.size()))));
	}

}
