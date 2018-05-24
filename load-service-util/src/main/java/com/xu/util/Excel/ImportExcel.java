package com.xu.util.Excel;

import com.alibaba.fastjson.JSON;
import com.sun.istack.internal.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuhongda on 2018/5/22
 * poi
 * dataplatform
 */

public class ImportExcel {

    private static Logger log = Logger.getLogger(ImportExcel.class);
    /**
     * 2003- 版本的excel
     */
    private final static String excel2003L = ".xls";
    /**
     *  2007+ 版本的excel
     */

    private final static String excel2007U = ".xlsx";

    /**
     * 将流中的Excel数据转成List<Map>
     *
     * @param in
     *            输入流
     * @param fileName
     *            文件名（判断Excel版本）
     * @param mapping
     *            字段名称映射
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> parseExcel(InputStream in, String fileName,
                                                       Map<String, String> mapping) throws Exception {
        // 根据文件名来创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet;
        Row row ;
        Cell cell;
        // 返回数据
        List<Map<String, Object>> ls = new ArrayList<>();

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            // 取第一行标题
            row = sheet.getRow(0);
            String[] title;
            if (row != null) {
                title = new String[row.getLastCellNum()];

                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    title[y] = (String) getCellValue(cell);
                }

            } else {
                continue;
            }
            log.info(JSON.toJSONString(title));

            // 遍历当前sheet中的所有行
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                Map<String, Object> m = new HashMap<>();
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String key = title[y];
                    m.put(mapping.get(key), getCellValue(cell));
                }
                ls.add(m);
            }

        }
        work.close();
        return ls;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr ;fileName
     *
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            //2003-
            wb = new HSSFWorkbook(inStr);
        } else if (excel2007U.equals(fileType)) {
            //2007+ SXSSFWorkbook
             wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        // 格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //// 格式化数字
        DecimalFormat df2 = new DecimalFormat("0");
        if (cell != null){
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        }

        return value;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("F:\\办公文档\\车辆管理2.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Map<String, String> m = new HashMap<>();
        m.put("车牌号", "carNumber");
        m.put("租车人", "rentBy");
        m.put("车辆状态", "carStatus");
        m.put("联系电话", "phoneNum");
        m.put("身份证", "idCardNum");
        m.put("现住址", "presentAddress");
        m.put("分公司", "firstParty");
        m.put("交车时间", "carDealTime");
        m.put("发动机号", "engineNum");
        m.put("车架号", "classNum");

       /* m.put("列一","列一");
        m.put("列二","列二");
        m.put("列三","列三");*/
        List<Map<String, Object>> ls = parseExcel(fis, file.getName(), m);
        System.out.println(ls.size());
        System.out.println(ls);

        System.out.println(JSON.toJSONString(ls));
    }

}
