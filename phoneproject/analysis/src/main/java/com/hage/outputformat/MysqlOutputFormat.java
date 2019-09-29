package com.hage.outputformat;

import com.hage.Utils.JDBCUtil;
import com.hage.convertor.DimensionConvertor;
import com.hage.kv.CommDimension;
import com.hage.kv.CountDurationValue;
import com.hage.kv.base.BaseDimension;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlOutputFormat extends OutputFormat<CommDimension, CountDurationValue> {

    private FileOutputCommitter committer = null;

    @Override
    public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        Connection connection = JDBCUtil.getInstance();
        try {
            connection.setAutoCommit(false);//关闭sql自动提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MySqlRecordWriter(connection);
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }

    /**
     * Get the {@link Path} to the output directory for the map-reduce job.
     *
     * @return the {@link Path} to the output directory for the map-reduce job.
     * @see FileOutputFormat#getWorkOutputPath(TaskInputOutputContext)
     */
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }

    private static class MySqlRecordWriter extends RecordWriter<CommDimension, CountDurationValue> {
        private Connection connection = null;//JDBC连接
        private PreparedStatement preparedStatement = null;//预编译sql
        private DimensionConvertor convertor = null;
        private int batchBound = 500;
        private int batchSize = 0;

        public MySqlRecordWriter(Connection connection) {
            //初始化连接&转换器
            this.connection = connection;
            this.convertor = new DimensionConvertor();
        }

        //向mysql写数据的核心方法
        @Override
        public synchronized void write(CommDimension key, CountDurationValue value) throws IOException, InterruptedException {
            String sql = "INSERT INTO tb_call VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `call_sum` = ?,`call_duration_sum` = ?;";

            //1.查询联系人&时间维度ID
            try {
                int contactId = convertor.getDimensionID(key.getContactDimension());
                int dateId = convertor.getDimensionID(key.getDateDimension());
                //2.获取通话次数&通话时长
                int count = value.getCount();
                int duration = value.getDuration();
                //拼接tb_call表的主键
                String callKey = contactId + "_" + dateId;

                //初始化预编译的sql
                if (preparedStatement == null) {
                    preparedStatement = connection.prepareStatement(sql);
                }

                //给预编译sql赋值
                int i = 0;
                preparedStatement.setString(++i, callKey);
                preparedStatement.setInt(++i, dateId);
                preparedStatement.setInt(++i, contactId);
                preparedStatement.setInt(++i, count);
                preparedStatement.setInt(++i, duration);
                preparedStatement.setInt(++i, count);
                preparedStatement.setInt(++i, duration);

                //将数据放入缓存
                preparedStatement.addBatch();
                batchSize++;

                //当缓存数据条数超过预设边界时，执行sql并提交
                if (batchSize >= batchBound) {
                    preparedStatement.executeBatch();
                    connection.commit();
                    batchSize = 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if (preparedStatement != null) {
                try {
                    preparedStatement.executeBatch();
                    connection.commit();

                    JDBCUtil.close(connection, preparedStatement, null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
