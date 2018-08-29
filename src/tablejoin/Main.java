package tablejoin;

import module.GpsInfo;
import module.writable.JoinInfoWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;
import java.util.HashMap;

public class Main {

    public static HashMap<String, GpsInfo> hashMap = new HashMap<>();
    public static HashMap<String, GpsInfo> hashMap1 = new HashMap<>();

    public static void main(String[] args) throws Exception {
//		System.out.println(hashMap.size());
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf);
        Job job = Job.getInstance(conf, "gpshash");
        job.setJarByClass(tablejoin.Main.class);
//        job.setJar("D:\\eclipse\\workspace\\dataSplit\\classes\\artifacts\\dataSplit_jar\\dataSplit.jar");
        job.setMapperClass(GpsMapper.class);
        fs.delete(new Path("hdfs://master:9000/output/gps"));
        FileInputFormat.setInputPaths(job, new Path("hdfs://master:9000/data/gps"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://master:9000/output/gps"));
        if (!job.waitForCompletion(true))
            System.err.println("err");
        System.out.println(hashMap.size());

        job = Job.getInstance(conf, "tablejoin");
        String dic = "split";
        fs.delete(new Path("hdfs://master:9000/data/" + "mastertable"));
        job.setJarByClass(tablejoin.Main.class);
//        job.setJar("D:\\eclipse\\workspace\\dataSplit\\classes\\artifacts\\dataSplit_jar\\dataSplit.jar");
        job.setMapperClass(TableJoinMapper.class);
        job.setReducerClass(TableJoinReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinInfoWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(JoinInfoWritable.class);

        FileInputFormat.setInputPaths(job, new Path("hdfs://master:9000/data/" + dic));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://master:9000/data/" + "mastertable"));
        if (!job.waitForCompletion(true))
            return;
    }

}
