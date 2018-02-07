package step1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {
    private static String inPath="/Users/gengshu/IdeaProjects/imoocMapR/data/input/matrix2.txt";

    private static String outPath="/Users/gengshu/IdeaProjects/imoocMapR/data/output";

    public static void main(String[] args)throws Exception{
        Job job = new Job();
        job.setJarByClass(Main.class);
        job.setJobName("transmatrix");
        FileInputFormat.addInputPath(job,new Path(inPath));
        FileOutputFormat.setOutputPath(job,new Path(outPath));

        job.setMapperClass(Mapper1.class);
        job.setReducerClass(Reducer1.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        System.exit(job.waitForCompletion(true)?0:1);


    }
}
