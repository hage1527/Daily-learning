package com.hage;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ETLUtils {

    public static String getETlString(String line) {
        String[] split = line.split("\t");
        //1.过滤掉不合法数据
        if (split.length < 9) return null;
        //2.去除&符号两边的空格
        split[3] = split[3].replaceAll(" ", "");
        //3.\t换成&
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (i<9){
                if (i!=split.length-1){
                    stringBuffer.append(split[i]).append("\t");
                }else {
                    stringBuffer.append(split[i]);
                }
            }else {
                if (i!=split.length-1){
                    stringBuffer.append(split[i]).append("&");
                }else {
                    stringBuffer.append(split[i]);
                }
            }
        }
        return stringBuffer.toString();
    }
}
