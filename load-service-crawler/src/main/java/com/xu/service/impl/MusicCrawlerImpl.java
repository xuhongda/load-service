package com.xu.service.impl;

import com.xu.service.MusicCrawler;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import static com.xu.util.encoding.ExchangeEncoding.getUTF8XMLString;

/**
 * @author xuhongda on 2018/5/15
 * com.xu.service.impl
 * load-service-parent
 */
@Service
public class MusicCrawlerImpl implements MusicCrawler {
    private final static String URL="https://y.qq.com/";
    @Override
    public String listen(String keyWords) {
        return null;
    }

    @Override
    public String download(String keyWords) {
        return null;
    }

    private Object crawler(String keyWords){

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

        String keyWordsUtf = getUTF8XMLString(keyWords);
        String ct ="24";
        //暂时没有发现searchid 作用;
        String searchid = "60194761350";

        String qqmusic_ver = "1298";

        String jsonpCallback = "MusicJsonCallback0833551783364972";


        String url ="https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&" +
                "searchid="+searchid+"&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=20&w" +
                "="+keyWordsUtf+"" +
                "&g_tk=5381&jsonpCallback=MusicJsonCallback6815288917447431&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0";
        System.setProperty("webdriver.chrome.driver", "E:\\Seleniumhq\\chromedriver.exe");

        driver.get(url);

        String musicList  = driver.findElement(By.tagName("pr")).getText();
        return null;
    }



}
