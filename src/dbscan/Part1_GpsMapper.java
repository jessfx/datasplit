package dbscan;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Part1_GpsMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	private String compus="";

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		if(compus.compareTo("")==0){
			compus=ivalue.toString();
//			System.out.println(this.toString());
//			System.out.println(compus);
		}
		else{
			context.write(ivalue, new Text("#"+compus));
//			System.out.println(ivalue.toString());
		}
	}

}
