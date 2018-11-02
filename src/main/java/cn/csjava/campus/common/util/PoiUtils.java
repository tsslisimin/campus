package cn.csjava.campus.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/22
 */
public class PoiUtils {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream in, String filename) throws IOException {
        Workbook wb = null;
        if (filename.endsWith(EXCEL_XLS)) {
            //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (filename.endsWith(EXCEL_XLSX)) {
            // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;

    }

    public static <T> List<T> read(Workbook workbook, int sheet, int skip, Class<T> cls) throws Exception {
        return read(workbook, sheet, skip, cls, null);
    }

    public static <T> List<T> read(Workbook workbook, int sheet, int skip, Class<T> cls, Predicate<T> pre) throws Exception {
        Sheet rows = workbook.getSheetAt(sheet);
        int index = 0;
        List<T> items = new LinkedList<>();
        for (Row row : rows) {
            if (index > skip) {
                T t = cls.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                int cellIndex = 0;
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    if (fields.length == cellIndex) {
                        System.out.println("continue  " + cell + "  cellIndex " + cellIndex);
                        continue;
                    }
                    if (cellIndex != columnIndex) {
                        setField(t, fields[cellIndex], "");
                        cellIndex++;
                    }
                    setField(t, fields[cellIndex], cell);
                    cellIndex++;
                    if (pre != null && !pre.test(t)) {
                        return Collections.emptyList();
                    }
                }
                items.add(t);
            }
            index++;

        }
        return items;
    }

    private static <T> void setField(T t, Field field, Object cell) throws Exception {

        Object target;

        field.setAccessible(true);
        if (cell != null && field.getType() == Date.class) {
            target = DATE_FORMAT.parse(cell.toString());
        } else {
            target = cell == null ? "" : cell.toString();
        }
        field.set(t, target);
    }

}