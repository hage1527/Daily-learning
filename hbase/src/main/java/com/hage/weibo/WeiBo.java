package com.hage.weibo;

import java.io.IOException;

public class WeiBo {

    public static void init() throws IOException {
        //创建表空间
        WeiBoUtil.createNamespace(Constant.NAME_SPACE);
        //创建微博内容表
        WeiBoUtil.createTable(Constant.TABLE_CONTENT, 1, "info");
        //创建用户关系表
        WeiBoUtil.createTable(Constant.TABLE_RELEATION, 1, "attends","fans");
        //创建收件箱表
        WeiBoUtil.createTable(Constant.TABLE_INBOX, 100, "info");

    }

    public static void main(String[] args) throws IOException {
        //初始化
//        init();
        //关注
//        WeiBoUtil.addAttends("1001", "1002", "1003");

        //被关注的人发微博（多个人发微博）
//        WeiBoUtil.putData("1002", "info", "content", "今天天气真晴朗！");
//        WeiBoUtil.putData("1002", "info", "content","春困秋乏！");
//        WeiBoUtil.putData( "1003", "info", "content", "夏打盹！");
//        WeiBoUtil.putData( "1001", "info", "content","冬眠睡不醒！");
        //关注已经发过微博的人
//        WeiBoUtil.addAttends("1001", "1002");

        //获取关注人的微博
        WeiBoUtil.getWeibo("1002");
        //关注已经发过微博的人
//        WeiBoUtil.addAttends("1002", "1003");

        //获取关注人的微博
//        WeiBoUtil.getWeibo("1002");
        //取消关注
        WeiBoUtil.deleteAttends("1002","1001");

        //获取关注人的微博
        WeiBoUtil.getWeibo("1002");


    }
}
