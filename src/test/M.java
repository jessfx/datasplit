package test;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.TaskInputOutputContext;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class M extends Mapper<LongWritable, Text, NullWritable, Text> {
	
//	private MultipleOutputs<NullWritable, Text> mos;
//	
//	@Override
//	public void setup(Context context) throws IOException, InterruptedException{
//		super.setup(context);
//		mos=new MultipleOutputs<>(context);
//	}
//	
//	@Override
//	public void cleanup(Context context) throws IOException, InterruptedException{
//		super.cleanup(context);
//		mos.close();
//	}

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		
//		}
	}

}
