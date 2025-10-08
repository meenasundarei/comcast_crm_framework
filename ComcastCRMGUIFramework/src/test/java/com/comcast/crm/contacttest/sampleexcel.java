package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class sampleexcel {
	
	@Test
				public void sample() throws Exception {
					
					
					
					// TODO Auto-generated method stub
			WebDriver driver = new ChromeDriver();
					
					driver.manage().window().maximize();
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					driver.get("https://www.google.com/");
					// verify the page
					System.out.println("title is:"+ driver.getTitle());
					
					
					FileInputStream fis = new FileInputStream("./testdata/mm.xlsx");
					
					Workbook book = WorkbookFactory.create(fis)	;
					
			Sheet sh =book.getSheet("Sheet1");
				
					
					
							
							//identify the search box and store 

						WebElement element =driver.findElement(By.xpath("//textarea[@name='q']"));
						
						element.sendKeys("selenium");
						
						 List<WebElement> allsuggestions=driver.findElements(By.xpath("//span[text()='selenium']")); 
						 
						 System.out.println(allsuggestions.size());
						 int rowindex=0;
						 for(int i=0;i<allsuggestions.size();i++) {
							 
							 
							String data =allsuggestions.get(i).getText();
							
							System.out.println(data);
							
							Row row1 = sh.getRow(rowindex);
						    row1 = sh.createRow(rowindex);

							Cell cel = row1.createCell(0);
							
						//	cel.setCellType(CellType.STRING);
							cel.setCellValue(data);
							
							rowindex++;
						 }
						 FileOutputStream fos  = new FileOutputStream("./testdata/mm.xlsx");

							book.write(fos);
							
							book.close();
						 
						 System.out.println(element.getText());
						 element.click();

	}

}
