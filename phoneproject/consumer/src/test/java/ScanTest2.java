import com.hage.constant.constant;
import com.hage.utils.HBaseScanUtil;
import com.hage.utils.PropertiesUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ScanTest2 {
    //15369468720,2017-04,2017-06
    public static void main(String[] args) throws  IOException, ParseException {

        //获取每一组startRow和stopRow
        List<String[]> startStop = HBaseScanUtil.getStartStop("15369468720", "2017-04", "2018-02");

        Connection connection = ConnectionFactory.createConnection(constant.configuration);
        Table table = connection.getTable(TableName.valueOf(PropertiesUtil.getProperties().getProperty("hbase.table.name")));


        while (HBaseScanUtil.hasNext()) {

            String[] next = HBaseScanUtil.next();
            System.out.println(next[0] + next[1]);
            Scan scan = new Scan(Bytes.toBytes(next[0]), Bytes.toBytes(next[1]));
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                System.out.println(Bytes.toString(result.getRow()));
            }

        }


    }
}
