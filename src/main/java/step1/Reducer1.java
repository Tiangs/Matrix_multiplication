package step1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reducer1 extends Reducer<Text,Text,Text, Text> {

    private Text outkey = new Text();
    private Text outvalue = new Text();



    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        StringBuilder sb = new StringBuilder();
        for (Text value:values){
            sb.append(value+",");
        }
        String line = null;
        if (sb.toString().endsWith(",")){
            line = sb.substring(0,sb.length()-1);
        }



        outkey.set(key);
        outvalue.set(line);
        context.write(outkey,outvalue);
    }
}
