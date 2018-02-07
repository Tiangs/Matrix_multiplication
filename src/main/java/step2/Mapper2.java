package step2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//输入是matrix1
//最终要输出的是matrix1的行号（输出key）； 计算的最终结果（输出value）

public class Mapper2 extends Mapper<LongWritable,Text,Text,Text> {

    private Text outKey = new Text();
    private Text outValue = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {



        //获取行号
        String rowNumStr = value.toString().split(" ")[0];
        int rowNum = Integer.parseInt(rowNumStr);

        //获取 列号_值，列号_值，列号_值，列号_值....
        String[] rowInfo = value.toString().split(" ")[1].split(",");



        int sum;
        //循环step1output的行数次(5次)
        for(int i=0;i<Unit.getLines();i++) {

            //输出value，设置为sum
             sum = 0;

            //遍历 "列号_值" 数组(4次)
            for (int j = 0; j < rowInfo.length; j++) {

                //保存列号
                int col = Integer.parseInt(rowInfo[j].split("_")[0]);

                //保存值
                int val = Integer.parseInt(rowInfo[j].split("_")[1]);

                //获取units中对应的unit
                Unit u = Unit.findByRowCol(i+1, col);
   //             String colStr = String.valueOf(u.getCol());
                sum += val * (u.getValue());


            }
            outKey.set(rowNumStr);
            outValue.set(String.valueOf(i + 1) + "_" + String.valueOf(sum));
            context.write(outKey, outValue);
        /*


        for (int i=0;i<rowInfo.length;i++){

            //保存列号
            int col = Integer.parseInt(rowInfo[i].split("_")[0]);

            //保存值
            int val = Integer.parseInt(rowInfo[i].split("_")[1]);


            //这里要开始进行计算，两边对应的行列号相等的要进行乘法运算，然后记录到sum中


            //获取units中对应的unit
            Unit u = Unit.findByRowCol(rowNum,col);
            String colStr = String.valueOf(u.getCol());
            sum += val * (u.getValue()) ;


            outKey.set(rowNumStr);
            outValue.set(colStr+"_"+String.valueOf(sum));
            context.write(outKey,outValue);
        }

        */
        }
    }
}
