package step2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reducer2 extends Reducer<Text,Text,Text,Text> {

    private Text outKey = new Text();
    private Text outValue = new Text();


    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //将所有的字符串拼接就好了
        StringBuilder sb = new StringBuilder();
        for (Text value:values){
            sb.append(value+",");
        }




        //如果最终是"，"结尾，就把"，"取消掉
        String line = null;
        if (sb.toString().endsWith(",")){
            line = sb.substring(0,sb.length()-1);
        }



        outKey.set(key);
        outValue.set(line);
        context.write(outKey,outValue);

    }


}
