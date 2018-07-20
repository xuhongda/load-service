package com.xu.loadservicecrawler;

import com.alibaba.fastjson.JSON;
import com.xu.bean.Details;
import com.xu.bean.Pencancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author xuhongda on 2018/7/18
 * com.zhiyuan.dataplatform.common.gateway.component.crawler
 * dataplatform
 */
public class CarSoGou {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\Seleniumhq\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
        options.addArguments("--lang=" + "zh-CN");
        WebDriver driver = new ChromeDriver(options);
        String url = "https://www.sogou.com/sgo?query=%E8%BD%A6%E8%BE%86%E8%BF%9D%E7%AB%A0%E6%9F%A5%E8%AF%A2&hdq=sogou-clse-7221e5c8ec6b08ef-0099&lxod=0_18_1_-1_1&lxea=6-2-1-8.9.0.2180-3-CN4403-50-0-1-F79200711EA1212FD2C84C0BBD817991-13&lxoq=chel&lkx=0&ie=utf8";


        driver.get(url);
        try {
            Thread.sleep(800);
            WebElement box = driver.findElement(By.cssSelector("div.weizhang-box"));

            /**/
            List<WebElement> elements = box.findElements(By.cssSelector(".f-box"));
            WebElement webElement = elements.get(1);
            WebElement span = webElement.findElement(By.tagName("span"));
            System.out.println(span.getAttribute("style"));
            Actions actions = new Actions(driver);
            actions.moveToElement(span).click().perform();
            //
            //source
            WebElement selListBox = webElement.findElement(By.cssSelector(".selListBox"));
            //target
            List<WebElement> a = selListBox.findElements(By.tagName("a"));
            for (WebElement element : a) {
                System.out.println(element.getText());
                if ("粤".equals(element.getText())) {
                    //模拟鼠标移动，拖动点击，但是速度方面有点慢
                    //如果下拉列表元素全部加载出来的话，可以直接选定元素点击
                   /* actions.dragAndDrop(selListBox, element).click().perform();
                    actions.release();*/
                    element.click();

                }

            }
            //1 车牌
            WebElement span1 = box.findElement(By.cssSelector("span.input-b"));
            WebElement input = span1.findElement(By.tagName("input"));
            input.clear();
            input.sendKeys("T1735S");
            //2 发动机
            List<WebElement> span2 = box.findElements(By.cssSelector("span.input-b2"));
            WebElement input2 = span2.get(0).findElement(By.tagName("input"));
            input2.clear();
            input2.sendKeys("801695");
            //3 车架
            WebElement input3 = span2.get(1).findElement(By.tagName("input"));
            input3.clear();
            input3.sendKeys("142895");
            //click
            box.findElement(By.cssSelector("p.f-btn")).findElement(By.tagName("a")).click();
            Thread.sleep(1000);


            //判断网络因素
            List<WebElement> bbs = driver.findElements(By.cssSelector(".weizhang-box"));
            WebElement b = bbs.get(2);
            System.out.println(b.getText());
            if (b.getText().contains("抱歉，查询出错，请重试。")) {
                WebElement re = b.findElement(By.cssSelector("p.btn-p"));
                re.click();
                Thread.sleep(100);
                //click
                box.findElement(By.cssSelector("p.f-btn")).findElement(By.tagName("a")).click();
                Thread.sleep(100);
            }
            //
            Pencancy pencancy = new Pencancy();
            Document parse = Jsoup.parse(driver.getPageSource());
            Elements elementsByClass = parse.getElementsByClass("weizhang-box");
            Element parseBox = elementsByClass.get(3);
            Elements p = parseBox.getElementsByTag("p");
            String[] split = p.get(0).text().split("，");
            int i = 0;
            for (String s : split) {
                String regEx = "[^0-9]";
                Pattern pp = Pattern.compile(regEx);
                Matcher m = pp.matcher(s);

                if (i == 0) {
                    System.out.println(m.replaceAll("").trim());
                    //罚款
                    pencancy.setTotalFine(m.replaceAll("").trim());
                } else if (i == 1) {
                    System.out.println(m.replaceAll("").trim());
                    //扣分
                    pencancy.setTotalPointReducted(m.replaceAll("").trim());
                }
                i++;
            }
            Elements children = parseBox.children();
            Elements tbody = children.get(1).getElementsByTag("tbody");
            Elements trs = tbody.get(0).getElementsByTag("tr");
            List<Details> list = new ArrayList<>();
            int k = 0;
            for (Element element : trs) {
                if (k > 0) {
                    Details details = new Details();
                    Elements tds = element.getElementsByTag("td");
                    for (int j = 0; j < tds.size(); j++) {
                        if (j == 0) {
                            details.setId(Integer.parseInt(tds.get(0).text()));
                        } else if (j == 1) {
                            details.setTime(tds.get(1).text());
                        } else if (j == 2) {
                            details.setLocation(tds.get(2).text());
                        } else if (j == 3) {
                            details.setDescribe(tds.get(3).text());
                        } else if (j == 4) {
                            details.setFine(tds.get(4).text());
                        } else if (j == 5) {
                            details.setPoint(tds.get(5).text());
                        }
                    }
                    list.add(details);
                }
                k++;
            }
            if (list.size() == 0) {
                pencancy.setTotalFine("0");
            }


            pencancy.setTotalCount(Integer.valueOf(list.size()).toString());
            pencancy.setDetails(list);
            Object o = JSON.toJSON(pencancy);
            System.out.println(o);
            //判断返回
            if (list.size() > 0) {
                List<WebElement> weizhanBox = driver.findElements(By.cssSelector("div.weizhang-box"));
                WebElement element = weizhanBox.get(3);
                WebElement re = element.findElement(By.cssSelector("p.btn-p"));
                re.click();
            } else {
                List<WebElement> weizhanBox = driver.findElements(By.cssSelector("div.weizhang-box"));
                WebElement element = weizhanBox.get(2);
                WebElement re = element.findElement(By.cssSelector("p.btn-p"));
                re.click();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
