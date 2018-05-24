package com.xu.util.Excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将HSSFWorkbook 对象改换成 HSSFWorkbook 突破EXCSEL 六万多条限制 SXSSFWorkbook 突破大量数据导致的OOM
 * HSSFSheet==》》XSSFSheet ==》》SXSSFWorkbook
 * HSSFRow ===》》XSSFRow
 * HSSFCell ===》》XSSFCell
 * @author xuhongda on 2018/4/24
 * com.zhiyuan.riskcloud.portrait.service.Excel
 * dataplatform
 */
public class ExportExcel {
    /***
     * 构造方法
     */
    private ExportExcel() {

    }

    /***
     * 工作簿
     */
    private static /*HSSFWorkbook*//*XSSFWorkbook*/ SXSSFWorkbook workbook;

    /***
     * sheet
     */
    private static /*HSSFSheet*//*XSSFSheet */SXSSFSheet sheet;

    /***
     * 标题行开始位置
     */
    private static final int TITLE_START_POSITION = 0;

    /***
     * 时间行开始位置
     */
    private static final int DATEHEAD_START_POSITION = 1;

    /***
     * 表头行开始位置
     */
    private static final int HEAD_START_POSITION = 2;

    /***
     * 文本行开始位置
     */
    private static final int CONTENT_START_POSITION = 3;


    /**
     *
     * @param dataList
     *        对象集合
     * @param titleMap
     *        表头信息（对象属性名称->要显示的标题值)[按顺序添加]
     * @param sheetName
     *        sheet名称和表头值
     * @param res
     *          response 对象
     * @param fileNameAndPath
     *         文件完整路径名
     */
    public static void excelExport(List<?> dataList, Map<String, String> titleMap, String sheetName,String fileNameAndPath,HttpServletResponse res) {
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 标题行
        createTitleRow(titleMap, sheetName);
        // 时间行
        createDateHeadRow(titleMap);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        //设置自动伸缩
        autoSizeColumn(titleMap.size());
        // 写入处理结果
        try {

            OutputStream out = new FileOutputStream( fileNameAndPath);
            workbook.write(out);
            out.close();
            /*+++++++++++++++++++一以下载文件格式 ajax 异步请求不行 需要直接请求方式++++++++++++++++++++++++*/
            // String fileName = "XXX表";
            /*ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            res.reset();
            res.setContentType("application/vnd.ms-excel;charset=utf-8");
            res.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            ServletOutputStream out = res.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }*/
            /*+++++++++++++++++++++++++++++++++++++++++++++*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param sheetName
     *        sheetName
     */
    private static void initHSSFWorkbook(String sheetName) {
        //workbook = new HSSFWorkbook();
        //workbook=new XSSFWorkbook();
        workbook = new SXSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    /**
     * 生成标题（第零行创建）
     * @param titleMap 对象属性名称->表头显示名称
     * @param sheetName sheet名称
     */
    private static void createTitleRow(Map<String, String> titleMap, String sheetName) {
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, titleMap.size() - 1);
        sheet.addMergedRegion(titleRange);
        /*HSSFRow*/ /*XSSFRow*/SXSSFRow titleRow = sheet.createRow(TITLE_START_POSITION);
       /* HSSFCell*//*XSSFCell*/SXSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(sheetName);
    }

    /**
     * 创建时间行（第一行创建）
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createDateHeadRow(Map<String, String> titleMap) {
        CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, titleMap.size() - 1);
        sheet.addMergedRegion(dateRange);
        /*HSSFRow*//*XSSFRow*/SXSSFRow dateRow = sheet.createRow(DATEHEAD_START_POSITION);
        /*HSSFCell*//*XSSFCell*/SXSSFCell dateCell = dateRow.createCell(0);
        dateCell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    }

    /**
     * 创建表头行（第二行创建）
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createHeadRow(Map<String, String> titleMap) {
        // 第1行创建
        /*HSSFRow*//*XSSFRow*/SXSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
        int i = 0;
        for (String entry : titleMap.keySet()) {
           /* HSSFCell*/ /*XSSFCell*/SXSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(titleMap.get(entry));
            i++;
        }
    }

    /**
     *
     * @param dataList 对象数据集合
     * @param titleMap 表头信息
     */
    private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
        try {
            int i=0;
            for (Object obj : dataList) {
                /*HSSFRow*//*XSSFRow*/SXSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
                int j = 0;
                for (String entry : titleMap.keySet()) {
                    String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
                    Method m = obj.getClass().getMethod(method, null);
                    Object o =m.invoke(obj, null);
                    String value = null;
                    if (o != null){
                       value = o.toString();
                    }
                   // String value =   m.invoke(obj, null).toString();
                   /* HSSFCell*//*XSSFCell*/SXSSFCell textcell = textRow.createCell(j);
                    textcell.setCellValue(value);
                    j++;
                }
                i++;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 自动伸缩列（如非必要，请勿打开此方法，耗内存）
     * @param size 列数
     */
    private static void autoSizeColumn(Integer size) {
        //
        sheet.trackAllColumnsForAutoSizing();

        for (int j = 0; j < size; j++) {
            sheet.autoSizeColumn(j);
        }
    }
}
