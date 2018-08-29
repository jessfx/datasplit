package statistics.weekendANDnweekend;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import util.DateUtil;
/*
 * 周末：非周末，所有地点，人次
 */
public class DeviceReducer11 extends Reducer<Text, Text, Text, Text> {
	
	public static HashMap<String, String> weekend=new HashMap<>();
	public static HashMap<String, String> nweekend=new HashMap<>();

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
			if(!map.containsKey(strings[0]+strings[1])){
				map.put(strings[0]+strings[1], strings[0]+strings[1]);
			}
		}
		context.write(new Text("weekend"), new Text("nweekend	All,rc"));
		context.write(new Text(String.valueOf((weekend.size()))), new Text(String.valueOf((nweekend.size()))));
	}

}
