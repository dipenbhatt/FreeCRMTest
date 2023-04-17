package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventlistener;
	
	
	
      public TestBase()
	  {
		
		try {
			 prop=new Properties();
			FileInputStream ip=new FileInputStream("C:\\QA\\SeleniumWorkSpace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			
			prop.load(ip);
		} catch(FileNotFoundException e) {
			   e.printStackTrace();
		        }
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		}
      
      public static void initialization() {
    	  
    	 String browsername=  prop.getProperty("browser");
    	 
    	  if(browsername.equals("chrome")) {
    		  ChromeOptions  options=new ChromeOptions();
    		  options.addArguments("--remote-allow-origins=*");
    		  
    		  
    		  
    		  driver=new ChromeDriver(options);
    		  
    	  }
    	  else if(browsername.equals("FF")) {
    		  
    		  driver=new FirefoxDriver();
    	  }
           else if(browsername.equals("EG")) {
    		  
    		  driver=new EdgeDriver();
    	  }
    	  
    	  e_driver=new EventFiringWebDriver(driver);
    	  eventlistener=new WebEventListener();
    	  e_driver.register(eventlistener);
    	  driver=e_driver;
    	  
    	  driver.manage().window().maximize();
    	  driver.manage().deleteAllCookies();
    	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
    	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
    

    	  driver.get(prop.getProperty("url"));
    	  
    	  
      }
      
		
	  

}
