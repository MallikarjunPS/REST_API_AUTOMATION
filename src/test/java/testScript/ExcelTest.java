package testScript;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.TreeMap;

public class ExcelTest {

 
		public static void main(String[] args) 
		    { 
		        // Blank workbook 
		        XSSFWorkbook workbook = new XSSFWorkbook(); 
		  
		        // Create a blank sheet 
		        XSSFSheet sheet = workbook.createSheet("student Details"); 
		  
		        // This data needs to be written (Object[]) 
		        TreeMap<String, Object[]> data = new TreeMap<String, Object[]>(); 
		        data.put("1", new Object[]{ "ID", "NAME", "LASTNAME" }); 
		        data.put("2", new Object[]{ 1, "Pankaj", "Kumar" }); 
		        data.put("3", new Object[]{ 2, "Prakashni", "Yadav" }); 
		        data.put("4", new Object[]{ 3, "Ayan", "Mondal" }); 
		        data.put("5", new Object[]{ 4, "Virat", "kohli" }); 
		  
		        // Iterate over data and write to sheet 
		        Set<String> keyset = data.keySet(); 
		        int rownum = 0; 
		        for (String key : keyset) { 
		            // this creates a new row in the sheet 
		            Row row = sheet.createRow(rownum++); 
		            Object[] objArr = data.get(key); 
		            int cellnum = 0; 
		            for (Object obj : objArr) { 
		                // this line creates a cell in the next column of that row 
		                Cell cell = row.createCell(cellnum++); 
		                if (obj instanceof String) 
		                    cell.setCellValue((String)obj); 
		                else if (obj instanceof Integer) 
		                    cell.setCellValue((Integer)obj); 
		            } 
		        } 
		        try { 
		            // this Writes the workbook gfgcontribute 
		            FileOutputStream out = new FileOutputStream(new File("gfgcontribute.xlsx")); 
		            workbook.write(out); 
		            out.close(); 
		            System.out.println("gfgcontribute.xlsx written successfully on disk."); 
		        } 
		        catch (Exception e) { 
		            e.printStackTrace(); 
		        } 
		    } 
		} 

