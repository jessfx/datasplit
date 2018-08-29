package statistics.kuaqu;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ResultMapper extends Mapper<LongWritable, Text, Text, ObjectWritable> {
	
	public Text text=new Text(),sText=new Text();
	public ObjectWritable objectWritable=new ObjectWritable();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] strings=ivalue.toString().split(",");
		text.set(strings[0]+","+strings[1]);
		sText.set(strings[2]);
		objectWritable.set(sText);
		context.write(text, objectWritable);
	}

}
