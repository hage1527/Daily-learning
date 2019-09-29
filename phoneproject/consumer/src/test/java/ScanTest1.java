import com.hage.constant.constant;
import com.hage.utils.HBaseFilterUtil;
import com.hage.utils.PropertiesUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class ScanTest1 {
    public static void main(String[] args) throws IOException {

        Filter filter = HBaseFilterUtil.gteqFilter("f1", "buildTime", Bytes.toBytes("2017-04"));
        Filter filter1 = HBaseFilterUtil.lteqFilter("f1", "buildTime", Bytes.toBytes("201706"));

        Filter filter2 = HBaseFilterUtil.andFilter(filter, filter1);

        Filter filter3 = HBaseFilterUtil.eqFilter("f1", "call1", "15369468720");
        Filter filter4 = HBaseFilterUtil.eqFilter("f1", "call2", "15369468720");

        Filter filter5 = HBaseFilterUtil.orFilter(filter3, filter4);

        Filter filter6 = HBaseFilterUtil.andFilter(filter2, filter5);

        Connection connection = ConnectionFactory.createConnection(constant.configuration);
        Table table = connection.getTable(TableName.valueOf(PropertiesUtil.getProperties().getProperty("hbase.table.name")));
        Scan scan = new Scan();
        scan.setFilter(filter6);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            for (Cell cell : result.rawCells()) {
                System.out.println("rowkey:"+Bytes.toString(CellUtil.cloneRow(cell))+
                "column:"+Bytes.toString(CellUtil.cloneQualifier(cell))
                +"value:"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        table.close();
        connection.close();
    }
}
