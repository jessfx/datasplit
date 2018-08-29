package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public Calendar calendar=Calendar.getInstance();
	public SimpleDateFormat simpleDateFormat;
	
	public DateUtil(String formatString){
		simpleDateFormat=new SimpleDateFormat(formatString);
	}
	
	public int dateToWeekday(String date) throws ParseException{
		calendar.setTime(simpleDateFormat.parse(date));
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}

}
