package step2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import step1.Main;
import step1.Mapper1;
import step1.Reducer1;

public class Main2 {

    private static String inPath="/Users/gengshu/IdeaProjects/imoocMapR/data/input/matrix1.txt";

    private static String outPath="/Users/gengshu/IdeaProjects/imoocMapR/data/output2";

    public static void main(String[] args)throws Exception{
//
//        //完成unit类的读取赋值
        Unit.assign();

        Job job = new Job();
        job.setJarByClass(Main.class);
        job.setJobName("multiplication");
        FileInputFormat.addInputPath(job,new Path(inPath));
        FileOutputFormat.setOutputPath(job,new Path(outPath));

        job.setMapperClass(Mapper2.class);
        job.setReducerClass(Reducer2.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        System.exit(job.waitForCompletion(true)?0:1);


    }
}
