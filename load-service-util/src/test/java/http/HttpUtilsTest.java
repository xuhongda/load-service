package http;

import bean.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.util.httpClient.HttpClientUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        JSONObject jsonObject1 = httpClientUtils.httpPost("http://localhost:8084/http/post", student);
        System.out.println(jsonObject1);
    }

    @Test
    public void test2(){
        String str="{\"name\":\"xu\",\"age\":18}";

        Student student = new Student("xu",18);
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        httpClientUtils.httpPost("http://localhost:8084/http/post",str);
    }

    @Test
    public void test3(){
        System.setProperty("webdriver.chrome.driver", "E:\\Seleniumhq\\chromedriver.exe");
        String url =  "https://ipcrs.pbccrc.org.cn/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
        options.addArguments("--lang=" + "zh-CN");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        By body = By.tagName("body");
        String text = driver.findElement(body).getText();
        String page = driver.getPageSource();
        //System.out.println(text);
        System.out.println(page);


    }

}
