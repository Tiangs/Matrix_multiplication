package step2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Unit {
    private static int lines=0;
    private int row;
    private int col;
    private int value;
    public static List<Unit> units = new ArrayList<Unit>();
    private static String path = "/Users/gengshu/IdeaProjects/imoocMapR/data/input/step1_output.txt";


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public static int getLines() {
        return lines;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "row=" + row +
                ", col=" + col +
                ", value=" + value +
                '}';
    }

    //将step1 output文件中的信息扒出来，到生成的容器当中
    public static  void assign(){
        File f = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String str = null;

            //按行读取step1output文件中的内容
            while(  ( str = br.readLine())!=null){
                lines++;



                //保存每一行的行号，叫做rowNum
                int rowNum = Integer.parseInt(str.split(" ")[0]);



                //保存每一行中的列和值的信息，存到col_vals的数组当中
                String[] col_vals = str.split(" ")[1].split(",");

                //循环，在col_vals数组当中，取出列值和值
                for(String col_val:col_vals){
                    //创建一个Unit实例
                    Unit u = new Unit();

                    //保存行号到实例中
                    u.setRow(rowNum);

                    //将列值保存到colNum中
                    int colNum = Integer.parseInt(col_val.split("_")[0]);

                    //将值保存到val中
                    String val = col_val.split("_")[1];

                    //保存列
                    u.setCol(colNum);

                    //保存值
                    u.setValue(Integer.parseInt(val));
                    units.add(u);

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //通过行列坐标查询
    public static Unit findByRowCol(int row , int col){

        //遍历，如果符合标准就把unit实例返回
        for (Unit u : units){
            if (row == u.getRow() && col == u.getCol()){
                return u;
            }
        }

        return null;
    }
//    public static void main(String[] args){
//        assign();
//        System.out.println(getLines());
//    }

}
