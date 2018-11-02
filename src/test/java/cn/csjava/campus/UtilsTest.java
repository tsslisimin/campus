package cn.csjava.campus;

import cn.csjava.campus.common.util.TableUtils;
import org.junit.Test;

/**
 * @author：hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
public class UtilsTest {


    @Test
    public void test() {
        TableUtils.genCreateTableSql("cn.csjava.campus.venue.document.VenuesJoinEntity");
    }
}
