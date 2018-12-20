package org.emi.GestionDesRH.personneTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestErrorNavigation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testErrorNavigation() throws Exception {
    driver.get("http://localhost:8080/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("notadmin@emi.ac.ma");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("notposs");
    driver.findElement(By.name("submit")).click();
  }
  
  @Test
  public void testNavigationReussite() throws Exception {
    driver.get("http://localhost:8080/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin@emi.ac.ma");
    driver.findElement(By.name("password")).click();
//    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminadmin");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("Etudiants")).click();
    driver.findElement(By.id("navbardrop")).click();
    driver.findElement(By.linkText("Permanents")).click();
    driver.findElement(By.id("navbardrop")).click();
    driver.findElement(By.linkText("Vacataires")).click();
    driver.findElement(By.linkText("Cadres administratifs")).click();
    driver.findElement(By.linkText("Archive etudiants")).click();
    driver.findElement(By.linkText("GRH")).click();
    driver.get("http://localhost:8080/logout");
  }
  
  @Test
  public void testAjoutReussit() throws Exception {
	  driver.get("http://localhost:8080/login");
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin@emi.ac.ma");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("adminadmin");
	    driver.findElement(By.name("submit")).click();
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Etudiants")).click();
    driver.findElement(By.id("btnAjouter")).click();
    driver.findElement(By.id("nomEt")).click();
    driver.findElement(By.id("nomEt")).sendKeys("ayman");
    driver.findElement(By.id("prenomEt")).click();
    driver.findElement(By.id("prenomEt")).sendKeys("bahhou");
    driver.findElement(By.id("dateNaissanceEt")).click();
    driver.findElement(By.id("dateNaissanceEt")).sendKeys("1996-08-05");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=concat('formulaire d', \"'\", 'inscription')])[1]/following::div[5]")).click();
    driver.findElement(By.id("cinEt")).click();
    driver.findElement(By.id("cinEt")).sendKeys("K458168");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Image'])[1]/following::div[2]")).click();
    driver.findElement(By.id("nomPrenomArabeEt")).click();
    driver.findElement(By.id("nomPrenomArabeEt")).sendKeys("arabic name");
    driver.findElement(By.id("matriculeEt")).click();
    driver.findElement(By.id("matriculeEt")).sendKeys("4518614");
    driver.findElement(By.id("dateInscriptionEt")).click();
    driver.findElement(By.id("dateInscriptionEt")).sendKeys("2016");
    driver.findElement(By.id("telephoneEt")).click();
    driver.findElement(By.id("telephoneEt")).sendKeys("0675849645");
    driver.findElement(By.id("emailPersoEt")).click();
    driver.findElement(By.id("emailPersoEt")).sendKeys("ayman.bahhou@gmail.com");
    driver.findElement(By.id("emailProEt")).click();
    driver.findElement(By.id("emailProEt")).sendKeys("ayman@emi.ac.ma");
    driver.findElement(By.id("adresseEt")).click();
    driver.findElement(By.id("adresseEt")).sendKeys("meknes");
    driver.findElement(By.id("motDePasseEt")).click();
    driver.findElement(By.id("motDePasseEt")).sendKeys("bahhoubahhou");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Mot de passe :'])[1]/following::input[2]")).click();
    driver.get("http://localhost:8080/logout");
  }
  
  @Test
  public void testErreurDajout() throws Exception {
	  driver.get("http://localhost:8080/login");
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin@emi.ac.ma");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("adminadmin");
	    driver.findElement(By.name("submit")).click();
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Etudiants")).click();
    driver.findElement(By.id("btnAjouter")).click();
    driver.findElement(By.id("nomEt")).click();
    driver.findElement(By.id("nomEt")).sendKeys("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
    driver.findElement(By.id("prenomEt")).click();
    driver.findElement(By.id("prenomEt")).sendKeys("aaaaaaaa");
    driver.findElement(By.id("dateNaissanceEt")).click();
    driver.findElement(By.id("dateNaissanceEt")).click();
    driver.findElement(By.id("dateNaissanceEt")).sendKeys("2018-12-13");
    driver.findElement(By.id("cinEt")).click();
    driver.findElement(By.id("cinEt")).sendKeys("a555555");
    driver.findElement(By.id("nomPrenomArabeEt")).click();
    driver.findElement(By.id("nomPrenomArabeEt")).sendKeys("aaaaaa");
    driver.findElement(By.id("matriculeEt")).click();
    driver.findElement(By.id("matriculeEt")).sendKeys("11111111");
    driver.findElement(By.id("dateInscriptionEt")).sendKeys("2018");
    driver.findElement(By.id("telephoneEt")).click();
    driver.findElement(By.id("telephoneEt")).sendKeys("11111111");
    driver.findElement(By.id("emailPersoEt")).click();
    driver.findElement(By.id("emailPersoEt")).click();
    driver.findElement(By.id("emailPersoEt")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=emailPersoEt | ]]
    driver.findElement(By.id("emailPersoEt")).sendKeys("aaaaa@gmail.com");
    driver.findElement(By.id("emailProEt")).click();
    driver.findElement(By.id("emailProEt")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=emailProEt | ]]
    driver.findElement(By.id("emailProEt")).sendKeys("aaaaa@emi.ac.ma");
    driver.findElement(By.id("adresseEt")).click();
    driver.findElement(By.id("adresseEt")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=adresseEt | ]]
    driver.findElement(By.id("adresseEt")).sendKeys("test");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Archive etudiants'])[1]/following::div[1]")).click();
    driver.findElement(By.id("motDePasseEt")).click();
    driver.findElement(By.id("motDePasseEt")).sendKeys("test");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Mot de passe :'])[1]/following::input[2]")).click();
    driver.get("http://localhost:8080/logout");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
