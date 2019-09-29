package com.hage.testcompress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * 压缩解压缩
 */
public class TestCompress {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        comparess("F:\\hadoop\\算法导论（第三版）.pdf","org.apache.hadoop.io.compress.GzipCodec");
        deComparess("F:\\hadoop\\算法导论（第三版）.pdf.gz");

    }

    private static void deComparess(String fileName) throws IOException {
        //1.获取文件输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        //2.获取编码器
        Configuration configuration = new Configuration();
        CompressionCodecFactory codecFactory = new CompressionCodecFactory(configuration);
        CompressionCodec codec = codecFactory.getCodec(new Path(fileName));
        //3.获取压缩输入流
        CompressionInputStream codecInputStream = codec.createInputStream(fis);
        //4.获取文件输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName + "hage"));
        //5.输入输出流对拷
        IOUtils.copyBytes(codecInputStream, fos, configuration);
        //6.关闭资源
        IOUtils.closeStream(codecInputStream);
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);

    }

    private static void comparess(String fileName, String method) throws IOException, ClassNotFoundException {
            //获得文件输入流
            FileInputStream fis = new FileInputStream(new File(fileName));
            //获取编解码器
            Class codecClass = Class.forName(method);
            Configuration configuration = new Configuration();
            //This class encapsulates a streaming compression/decompression pair.
            CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, configuration);
            //获取输出流
            FileOutputStream fos = new FileOutputStream(new File(fileName+codec.getDefaultExtension()));
            //获取压缩流
            CompressionOutputStream codecOutputStream = codec.createOutputStream(fos);
            //输入输出流对拷
            IOUtils.copyBytes(fis, codecOutputStream, configuration);
            //流的关闭
            IOUtils.closeStream(fis);
            IOUtils.closeStream(codecOutputStream);
            IOUtils.closeStream(fos);


    }
}
