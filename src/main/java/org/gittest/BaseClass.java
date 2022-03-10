package org.gittest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	static WebDriver driver;
	public static WebDriver BrowserLaunch(String browsername)
	{
		switch(browsername)
		{
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			default:
				System.err.println("Invalid Browser Name");
		}
		return driver;
	}
public static void urlLaunch(String url) {
	driver.get(url);
driver.manage().window().maximize();	
}
public static void implicitWait(long sec) {
	
driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
}
public static void quit() {
	driver.quit();
}
public static void sendkeys(WebElement e,String value) {
	e.sendKeys(value);
	
}
public static void btnclick(WebElement e) {
	e.click();
}

public static String getCurrentUrl() {
	return driver.getCurrentUrl();
}
public static String getTitle() {
	return driver.getTitle();
}
public static String getAttribute(WebElement e) {
	return e.getAttribute("value");
}
public static String getText(WebElement e) {
	return e.getText();
}

public static String getExcelData(String filename,String sheetname,int rowno,int cellno) throws IOException {
	File f=new File("C:\\Users\\ELCOT\\eclipse-workspace\\AdactinHotel\\src\\test\\resources\\ExcelData\\"+filename+".xlsx");
	FileInputStream is=new FileInputStream(f);
	Workbook w=new XSSFWorkbook(is);
	Sheet sheet=w.getSheet(sheetname);
	Row r=sheet.getRow(rowno);
	Cell cell=r.getCell(cellno);
	int type=cell.getCellType();
	String value=null;
	if(type==1) {
		value=cell.getStringCellValue();
	}else {
		value=String.valueOf((long)cell.getNumericCellValue());
		}
	return value;


}
}
