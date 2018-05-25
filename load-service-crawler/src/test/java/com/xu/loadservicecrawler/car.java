package com.xu.loadservicecrawler;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



/**
 * @author luofeng ��ȡ���˼���Υ�����
 *
 */
public class car {
	public static void main(String[] args) throws InterruptedException {
		int HIGHT = -90;
		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E6%9C%BA%E5%8A%A8%E8%BD%A6%E8%BF%9D%E7%AB%A0%E6%9F%A5%E8%AF%A2&rsv_pq=fb220dd50000696f&rsv_t=b229Bk00p%2BqpbaK5wBrq41JlZm1ELjZWFcSVW3s3SH2oNCRoGOyvUvsycwE&rqlang=cn&rsv_enter=1&rsv_sug3=6&rsv_sug1=1&rsv_sug7=100";
		//url="https://bl.cx580.com/content/index.html?userType=BD2016H5&carNumber=%E8%8B%8FA0Y2G7&carCode=355234&engineNumber=241261&token=FZWNPdLCmqk7VdS8mTwEqVUtt5jibGw1oOsylmKy3i8%3D#/otherQuery";
		/*System.setProperty("webdriver.ie.driver", "E:\\Seleniumhq\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();*/
		
		System.setProperty("webdriver.chrome.driver", "E:\\Seleniumhq\\chromedriver.exe");
	    ChromeOptions options = new ChromeOptions();
	    //options.addArguments("--user-data-dir=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
	    options.addArguments("--start-maximized","allow-running-insecure-content", "--test-type");
	    options.addArguments("--lang=" + "zh-CN");
	    WebDriver driver  = new ChromeDriver(options);
	    
	    try{
	    	//driver.switchTo().defaultContent();	
			driver.get(url);
			//driver.manage().window().maximize()
			Thread.sleep(3000);
			// 选择省份点击
	        WebElement webElement = driver.findElement(By.className("op-weizhang-shortname"));
	        WebElement webElement2 = webElement.findElement(By.className("c-dropdown2-btn-group"))
	                .findElement(By.className("c-dropdown2-btn-icon-border"));
	        WebElement province=webElement.findElement(By.className("c-dropdown2-btn-group"))
	                .findElement(By.className("c-dropdown2-btn"));
	        Thread.sleep(1000);
	        String selectProvince=province.getText();
	        String provinceName="苏";
	        int hight=0;
	        if(!provinceName.endsWith(selectProvince)){
	            webElement2.click();
	            //Thread.sleep(2000);
	            Actions action = new Actions(driver);
	            Thread.sleep(2000);
	            WebElement web = webElement.findElement(By.className("c-dropdown2-menu"))
	                    .findElement(By.className("opui-scroll-ctrl-scroll")).findElement(By.className("opui-scroll-slider"));
	            //Thread.sleep(2000);
	            if(provinceName.equals("苏")){
	                hight=-50;
	            }else if(provinceName.endsWith("川")){
	                hight=60;
	            }else if(provinceName.endsWith("赣")){
	                hight=50;
	            }
	            Thread.sleep(1000);
	            System.out.println(hight);
	            Actions drag=action.dragAndDropBy(web, 0, hight);
	            Thread.sleep(2000);
	            drag.perform();
	            Thread.sleep(2000);
	            WebElement webElement3 = webElement.findElement(By.className("c-dropdown2-menu"))
	                    .findElement(By.className("c-dropdown2-menu-inner")).findElement(By.className("c-dropdown2-menubox"));
	            List<WebElement> webElement4 = webElement3.findElements(By.className("c-dropdown2-option"));
	           // Thread.sleep(1000);
	            for (WebElement webElement5 : webElement4) {
	            	//webElement5.click();
	            	System.out.println(webElement5.getText());
	                if (webElement5.getText().equals(provinceName)){
	                	Thread.sleep(1000);
	                    webElement5.click();
	                    break;
	                }
	            }

	        }
	        //Thread.sleep(5000);
			// 输入查询参数
						driver.findElement(By.className("op-weizhang-carid")).clear();
						driver.findElement(By.className("op-weizhang-carid")).sendKeys("11111");
						driver.findElement(By.className("op-weizhang-engineno")).clear();
						driver.findElement(By.className("op-weizhang-engineno")).sendKeys("222222");
						driver.findElement(By.className("op-weizhang-classno")).clear();
						driver.findElement(By.className("op-weizhang-classno")).sendKeys("3333333");
						driver.findElement(By.className("op-weizhang-vcode ")).sendKeys("yumy");
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	driver.quit();
	    }
	    
		
		
	}

	private static boolean existsWebElement(SearchContext searchContext, By by) {
		try {
			searchContext.findElement(by);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}