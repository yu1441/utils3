package com.yujing.kotlinapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Values_hdpi {
    public static void genDimen() {
        StringBuilder sb = new StringBuilder();
        PrintWriter out;
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<resources>\n");
            //dp
            for (int i = 0; i <= 500; i++) {
                sb.append("    <dimen name=\"dp" + i + "\">" + df.format(i * 1d) + "dp</dimen>\n");
            }
            //sp
            for (int i = 0; i <= 500; i++) {
                sb.append("    <dimen name=\"sp" + i + "\">" + df.format(i * 1d) + "sp</dimen>\n");
            }
            sb.append("</resources>");

            //这里是文件名，1 注意修改 sw 后面的值，和转换值一一对应  2 文件夹和文件要先创建好否则要代码创建
            String fileDef = "./app/src/main/res/values-hdpi/dimens.xml";
            File file = new File(fileDef);
            file.getParentFile().mkdirs();
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(sb.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("创建完成");
        }
    }

    public static void main(String[] args) {
        genDimen();
    }
}
