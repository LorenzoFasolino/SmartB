package SystemTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Model.Docente;
import Model.DocenteModel;

class LoginTest {
	
	 private WebDriver driver;
	 private String baseUrl;
	 private boolean acceptNextAlert = true;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private DocenteModel dm;
	 private Docente d;


	 @Test
	  public void testLoginDocenteOk() throws Exception {
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\alfre\\Desktop\\chromedriver.exe");
		 dm = new DocenteModel();
		 d = new Docente("Nome", "Cognome", "0512100001", "12345678", "doc.docente@unisa.it", "uff01");
		 dm.doSave(d);
		driver = new ChromeDriver();	
		String baseUrl="http://localhost:8080/SmartBooking/jsp/Login.jsp";
		driver.get(baseUrl);
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));

		
		
		
		email.sendKeys(d.getEmail());
		password.sendKeys(d.getPassword());
		
		WebElement submit = driver.findElement(By.id("loginButton"));
		submit.click();
	    dm.doDelete(d.getMatricola());

	    assertEquals("http://localhost:8080/SmartBooking/jsp/InfoDocente.jsp?mat="+d.getMatricola(), driver.getCurrentUrl());
	    driver.quit(); 

	  }
	 
	 
	 @Test
	  public void testLoginDocenteError() throws Exception {
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\alfre\\Desktop\\chromedriver.exe");
		 dm = new DocenteModel();
		 d = new Docente("Nome", "Cognome", "0512100001", "12345678", "doc.docente@unisa.it", "uff01");
		 dm.doDelete(d.getMatricola());
		 
		driver = new ChromeDriver();	  
		String baseUrl="http://localhost:8080/SmartBooking/jsp/Login.jsp";
		driver.get(baseUrl);
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
	
		email.sendKeys(d.getEmail());
		password.sendKeys(d.getPassword());
		
		WebElement submit = driver.findElement(By.id("loginButton"));
		submit.click();

	    assertEquals("http://localhost:8080/SmartBooking/jsp/Login.jsp?err=1", driver.getCurrentUrl());
	   // assertEquals(baseUrl,driver.getCurrentUrl());
	    driver.quit();
	  }
	 
	 


}
