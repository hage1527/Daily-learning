package com.hage.mr;

import com.hage.kv.CommDimension;
import com.hage.kv.ContactDimension;
import com.hage.kv.DateDimension;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.HashMap;

public class CountDurationMapper extends TableMapper<CommDimension, Text> {
    //公共维度
    private CommDimension commDemension = new CommDimension();
    //联系人维度
    private ContactDimension contactDimension = new ContactDimension();
    //时间维度
    private DateDimension dateDemension = new DateDimension();

    private Text v = new Text();
    //联系人字典
    private HashMap<String, String> contacts = null;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        contacts = new HashMap<>();
        contacts.put("15369468720", "李雁");
        contacts.put("19920860202", "卫艺");
        contacts.put("18411925860", "仰莉");
        contacts.put("14473548449", "陶欣悦");
        contacts.put("18749966182", "施梅梅");
        contacts.put("19379884788", "金虹霖");
        contacts.put("19335715448", "魏明艳");
        contacts.put("18503558939", "华贞");
        contacts.put("13407209608", "华啟倩");
        contacts.put("15596505995", "仲采绿");
        contacts.put("17519874292", "卫丹");
        contacts.put("15178485516", "戚丽红");
        contacts.put("19877232369", "何翠柔");
        contacts.put("18706287692", "钱溶艳");
        contacts.put("18944239644", "钱琳");
        contacts.put("17325302007", "缪静欣");
        contacts.put("18839074540", "焦秋菊");
        contacts.put("19879419704", "吕访琴");
        contacts.put("16480981069", "沈丹");
        contacts.put("18674257265", "褚美丽");
        contacts.put("18302820904", "孙怡");
        contacts.put("15133295266", "许婵");
        contacts.put("17868457605", "曹红恋");
        contacts.put("15490732767", "吕柔");
        contacts.put("15064972307", "冯怜云");
    }

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        //key:0x_13712341234_2017-05-02 12:23:55_时间戳_13598769876_flag_duration
        //切分key获取各个字段
        String rowkey = Bytes.toString(key.get());
        String[] split = rowkey.split("_");
        if (split[5] == "0")
            return;
        String caller = split[1];
        String buildTime = split[2];
        String callee = split[4];
        String duration = split[6];
        //2017-05-02 12:23:55
        String year = buildTime.substring(0, 4);
        String month = buildTime.substring(5, 7);
        String day = buildTime.substring(8, 10);

        //封装value
        v.set(duration);

        //主叫联系人维度
        contactDimension.setPhoneNum(caller);
        contactDimension.setName(contacts.get(caller));
        commDemension.setContactDimension(contactDimension);
        //年维度
        DateDimension yearDemension = new DateDimension(year, "-1", "-1");
        commDemension.setDateDimension(yearDemension);
        context.write(commDemension, v);
        //月维度
        DateDimension monthDemension = new DateDimension(year, month, "-1");
        commDemension.setDateDimension(monthDemension);
        context.write(commDemension, v);
        //日维度
        DateDimension dayDemension = new DateDimension(year, month, day);
        commDemension.setDateDimension(dayDemension);
        context.write(commDemension, v);

        //被叫联系人维度
        contactDimension.setPhoneNum(callee);
        contactDimension.setName(contacts.get(callee));
        commDemension.setContactDimension(contactDimension);
        //年维度
        commDemension.setDateDimension(yearDemension);
        context.write(commDemension, v);
        //月维度
        commDemension.setDateDimension(monthDemension);
        context.write(commDemension, v);
        //日维度
        commDemension.setDateDimension(dayDemension);
        context.write(commDemension, v);
    }
}
