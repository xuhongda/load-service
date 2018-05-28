package com.xu.loadservicecrawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadServiceCrawlerApplicationTests {
    private final static String URL="https://y.qq.com/";

    @Test
    public void contextLoads() {
    }

    @Test
    public void crawler(){
        System.setProperty("webdriver.chrome.driver", "E:\\Seleniumhq\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
        options.addArguments("--lang=" + "zh-CN");
        WebDriver driver = new ChromeDriver(options);
        driver.get(URL);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
