package com.hage.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class HttpClient {

    /**
     * 获取hdfs的客户端,并建立文件夹
     */
    @Test
    public void testMkDir() throws IOException {
        //获取文件系统
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://hadoop100:9000");
        FileSystem fs = FileSystem.get(configuration);
        fs.mkdirs(new Path("/client"));
        fs.close();
    }

    /**
     * 获取hdfs的客户端，并建立文件夹（优化）
     */
    @Test
    public void testMkDir2() throws Exception {
        Configuration configuration = new Configuration();
        //获取hdfs的客户端
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"),
                configuration,
                "hage");
        //创建目录
        fs.mkdirs(new Path("/client"));
        //客户端关闭
        fs.close();
    }

    /**
     * 文件上传
     * 途中不知道为啥乱七八糟出了点错，耽误了好长时间好坑
     */
    @Test
    public void testPut() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"),
                configuration, "hage");
        //2上传文件
        fileSystem.copyFromLocalFile(new Path("f:/hello.txt"),
                new Path("/client/hello.txt"));
        //3关闭资源
        fileSystem.close();
    }

    /**
     * 文件下载
     */
    @Test
    public void testGet() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2下载文件
        //boolean delSrc 是否将源文件删除
        // Path src 要下载的文件路径
        // Path dst 文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        fileSystem.copyToLocalFile(false,
                new Path("/client/hello.txt"),
                new Path("f:/hello2.txt"),
                true);
        //3关闭资源
        fileSystem.close();
    }
    /**
     * 文件删除
     */
    @Test
    public void testDelete() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2删除文件
        fileSystem.delete(new Path("/client/hello.txt"), true);
        //3关闭资源
        fileSystem.close();
    }
    /**
     * 文件改名
     */
    @Test
    public void testRename() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2删除文件
        fileSystem.rename(new Path("/client/hello.txt"), new Path("/client/hello1.txt"));
        //3关闭资源
        fileSystem.close();
    }
    /**
     * 获取指定目录下的文件名称，权限，长度，块信息
     */
    @Test
    public void testListFiles() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2获取文件信息
        //获取目录下的文件信息迭代器
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/client"), false);
        while(listFiles.hasNext())
        {
            LocatedFileStatus fileStatus = listFiles.next();
            //输出详情
            System.out.println("文件名称"+fileStatus.getPath().getName());
            System.out.println("文件权限"+fileStatus.getPermission());
            System.out.println("文件长度"+fileStatus.getLen());
            System.out.println("文件组"+fileStatus.getGroup());
            //获取存储的块信息
            BlockLocation[] locations = fileStatus.getBlockLocations();
            for (BlockLocation location:locations)
            {
                //获取存储块的主机节点
                String[] hosts = location.getHosts();
                for (String host:hosts)
                {
                    System.out.println(host);
                }
            }
        }
        //3关闭资源
        fileSystem.close();
    }
    /**
     * 文件改名
     */
    @Test
    public void testListStatus() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2判断是文件还是文件夹
        FileStatus[] listStatus = fileSystem.listStatus(new Path("/client"));
        for (FileStatus status:listStatus){
            if(status.isDirectory()){
                System.out.println("目录"+status.getPath().getName());
            }else{
                System.out.println("文件"+status.getPath().getName());
            }
        }
        //3关闭资源
        fileSystem.close();
    }

    /**
     *文件上传，是用io流
     */
    @Test
    public void testPutUseIo() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2创建本地文件输入流，读文件到内存
        FileInputStream fileInputStream = new FileInputStream("f:/hello.txt");
        //3创建hdfs文件输出流，从内容写入到hdfs文件系统
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/client/putio.txt"));
        //4流对拷
        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, configuration);
        //5关闭资源
        fileInputStream.close();
        fsDataOutputStream.close();
        fileSystem.close();
    }
    /**
     * 文件下载，使用io流
     */
    @Test
    public void testGetUseIO() throws Exception {
        //1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2创建hdfs文件输入流，从hdfs读文件到内存
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/client/hello.txt"));
        //3流对拷到系统输出流
        IOUtils.copyBytes(fsDataInputStream, System.out, configuration);
        //4关闭资源
        fsDataInputStream.close();
        fileSystem.close();
    }
    /**
     * 文件下载第一块的内容
     */
    @Test
    public void testGetFirstBlock() throws Exception {
        //1获取客户端
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2创建hdfs输入流
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/client/hadoop-2.7.2.tar.gz"));
        //3创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("f:/hadoop-part1");
        //4流的拷贝
        byte[] bytes = new byte[1024];
        for (int i=0;i<1024*128;i++){
            fsDataInputStream.read(bytes);
            fileOutputStream.write(bytes);
        }
        IOUtils.closeStream(fsDataInputStream);
        IOUtils.closeStream(fileOutputStream);
        //5资源释放
        fileSystem.close();
    }
    /**
     * 文件下载剩余块的内容
     */
    @Test
    public void testGetOtherBlock() throws Exception{
        //1获取客户端
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "hage");
        //2创建hdfs输入流
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/client/hadoop-2.7.2.tar.gz"));
        //3创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("f:/hadoop-part2");
        //4流的拷贝
        fsDataInputStream.seek(1024*1024*128);
        IOUtils.copyBytes(fsDataInputStream, fileOutputStream, configuration);
        //5资源释放
        IOUtils.closeStream(fsDataInputStream);
        IOUtils.closeStream(fileOutputStream);
        fileSystem.close();
    }






}
