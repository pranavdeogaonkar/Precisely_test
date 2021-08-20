package precisely.excel.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	
	
public  ArrayList<Object[]> getDataFromExcel() throws IOException {
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
        String excelFilePath = "C:\\Users\\PRANAV\\eclipse-workspace\\Precisely_Test\\src\\main\\java\\precisely_testdata\\Precisely_Testdata.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
       XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheet("TestData");
        
         
        //int RowCount = firstSheet.getLastRowNum() + 1;
        
        
        //for(int RowNum = 2;RowNum<=RowCount;RowNum++) {
        XSSFRow row = (XSSFRow) firstSheet.getRow(1);
        
           
            XSSFCell cell = row.getCell(0);
        	String First_Name = cell.getStringCellValue();
        	
        
        	XSSFCell cell_lastname = row.getCell(1);
        	String Last_name = cell_lastname.getStringCellValue();
        	
        	XSSFCell cell_company = row.getCell(2);
        	String Company = cell_company.getStringCellValue();
        	
        	XSSFCell cell_email = row.getCell(3);
        	String Email = cell_email.getStringCellValue();
        	
        	XSSFCell cell_phone = row.getCell(4);
        	String Phone = cell_phone.getStringCellValue();
        	
        	XSSFCell cell_loc = row.getCell(5);
        	String Location = cell_loc.getStringCellValue();
        	
        	XSSFCell cell_industry = row.getCell(6);
        	String Industry = cell_industry.getStringCellValue();
        	
        	XSSFCell cell_comment = row.getCell(7);
        	String Comment = cell_comment.getStringCellValue();
        	
        	
           Object obj[] = {First_Name,Last_name,Company,Email,Phone,Location,Industry,Comment} ;
           data.add(obj);
            
        
      
        
        workbook.close();
        inputStream.close();
        
        return data;
   

}
}


