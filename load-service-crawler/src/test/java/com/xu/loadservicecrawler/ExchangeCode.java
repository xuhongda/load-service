package com.xu.loadservicecrawler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>
 *     编码转换工具类
 * </p>
 * @author xuhongda on 2018/5/16
 * com.xu.loadservicecrawler
 * load-service-parent
 */

public class ExchangeCode {


    public static void main(String[] args) {
        System.out.println(getUTF8XMLString("晴天"));
    }

    public static String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString;
        String xmlUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }
}
