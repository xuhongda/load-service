package com.xu.util.Excel;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuhongda on 2018/4/24
 * com.zhiyuan.riskcloud.portrait.service.Excel
 * dataplatform
 */

public class ExportExcelService {

    public String export(List<?> list,String path, HttpServletResponse response){
        Map<String,String> titleMap = new LinkedHashMap<>();


        titleMap.put("userId", "用户ID");
        titleMap.put("mobile", "手机");
        titleMap.put("targetTimeModel", "目标日期");
        titleMap.put("idcardProvince", "身份证籍贯");
        titleMap.put("identificationTimeModel", "实名回调时间");
        // ==>>
        titleMap.put("bindCardModel", "是否绑卡");
        //===>>>
        titleMap.put("regisTimeModel", "注册时间");

        String sheetName = "xxxx信息";
        System.out.println("start导出");
        Long start =System.currentTimeMillis();
        String fileName = fileName();
        path+=fileName;
        ExportExcel.excelExport(list, titleMap, sheetName,path,response);
        Long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时："+(end-start)+"ms");
        return fileName;
    }


    private String fileName(){
        String fileName = "小宝金融用户画像";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH时-mm分-ss秒");
        String date = df.format(new Date());
        date+=".xlsx";
        fileName += date;
        return fileName;
    }

    /**
     * 实现生成文件下载，controller  层 void
     * @param res
     * @param path 盘符
     * @param fileName 文件名
     */
    public void download(HttpServletResponse res ,String path,String fileName){

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {

            InputStream inputStream = new FileInputStream(new File(path+fileName));

            res.reset();
            res.setContentType("application/vnd.ms-excel;charset=utf-8");
            res.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName).getBytes(), "iso-8859-1"));

            //输出流
            ServletOutputStream out = res.getOutputStream();

            bis = new BufferedInputStream(inputStream);
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
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}