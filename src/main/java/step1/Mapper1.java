package step1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Mapper1 extends Mapper<LongWritable,Text,Text,Text>  {

    private Text outkey = new Text();

    private Text outvalue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] rowAndLine = value.toString().split(" ");

        //获取行号
        String row = rowAndLine[0];

        //获取列的信息
        String[] lines = rowAndLine[1].split(",");

        for (int i=0;i<lines.length;i++){

            //获取列号
            String column = lines[i].split("_")[0];

            //获取值
            String valueStr = lines[i].split("_")[1];

            //map阶段的输出
            //key：列号
            //value：行号_值
            outkey.set(column);
            outvalue.set(row+"_"+valueStr);

            context.write(outkey,outvalue);

        }



    }
}
