package export;
import com.xu.util.Excel.ExportExcel;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author xuhongda on 2018/4/24
 * export
 * dataplatform
 */
public class CustomerExportTest {


    @Test
    public void test(){
        //模拟数据开始
        List<Employee> staffs = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Employee staff = new Employee(i, i+"group", 1900+i, 12, 25, 2500+i);
            staffs.add(staff);
        }
        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("name", "姓名");
        titleMap.put("clazz", "组号");
        titleMap.put("year", "年份");
        titleMap.put("month", "月份");
        titleMap.put("day", "天");
        titleMap.put("salary", "薪资");
        String sheetName = "信息导出";
        //模拟数据结束
        System.out.println("start导出");
        long start = System.currentTimeMillis();
        ExportExcel.excelExport(staffs, titleMap, sheetName, "D:\\迅雷下载\\xxxx.xlsx", new HttpServletResponse() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String name) {
                return false;
            }

            @Override
            public String encodeURL(String url) {
                return null;
            }

            @Override
            public String encodeRedirectURL(String url) {
                return null;
            }

            @Override
            public String encodeUrl(String url) {
                return null;
            }

            @Override
            public String encodeRedirectUrl(String url) {
                return null;
            }

            @Override
            public void sendError(int sc, String msg) throws IOException {

            }

            @Override
            public void sendError(int sc) throws IOException {

            }

            @Override
            public void sendRedirect(String location) throws IOException {

            }

            @Override
            public void setDateHeader(String name, long date) {

            }

            @Override
            public void addDateHeader(String name, long date) {

            }

            @Override
            public void setHeader(String name, String value) {

            }

            @Override
            public void addHeader(String name, String value) {

            }

            @Override
            public void setIntHeader(String name, int value) {

            }

            @Override
            public void addIntHeader(String name, int value) {

            }

            @Override
            public void setStatus(int sc) {

            }

            @Override
            public void setStatus(int sc, String sm) {

            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public String getHeader(String name) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String name) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return null;
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }

            @Override
            public void setCharacterEncoding(String charset) {

            }

            @Override
            public void setContentLength(int len) {

            }

            @Override
            public void setContentLengthLong(long len) {

            }

            @Override
            public void setContentType(String type) {

            }

            @Override
            public void setBufferSize(int size) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void flushBuffer() throws IOException {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public void setLocale(Locale loc) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时："+(end-start)+"ms");
    }


    @Test
    public void testTongji(){

        //模拟数据开始
        List<Employee> staffs = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Employee staff = new Employee(i, i+"group", 1900+i, 12, 25, 2500+i);
            staffs.add(staff);
        }
        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("name", "序号");
        titleMap.put("clazz", "车牌号码");
        titleMap.put("year", "所属分组");
        titleMap.put("month", "车主");
        titleMap.put("day", "起始时间");
        titleMap.put("salary", "结束时间");
        titleMap.put("salary", "时长");
        titleMap.put("salary", "行驶里程");
        titleMap.put("salary", "油耗");
        titleMap.put("salary", "最大速度");
        titleMap.put("salary", "起始位置");
        titleMap.put("salary", "结束位置");


    }
}
