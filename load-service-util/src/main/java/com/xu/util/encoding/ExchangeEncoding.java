package com.xu.util.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <P>
 *     编码工具转换类
 * </P>
 * @author xuhongda on 2018/5/16
 * com.xu.util.encoding
 * load-service-parent
 */

public class ExchangeEncoding {

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
