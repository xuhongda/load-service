package http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.util.httpClient.HttpClientUtils;
import org.junit.Test;

/**
 * @author xuhongda on 2018/5/24
 * http
 * load-service-parent
 */

public class HttpUtilsTest {
    @Test
    public void test(){
        String str="{\"name\":\"xu\",\"age\":18}";
        JSONObject student = JSON.parseObject(str);
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        JSONObject jsonObject1 = httpClientUtils.httpPost("http://localhost:8088/http/post", student);
        System.out.println(jsonObject1);
    }
}
