package testScript;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;


public class Readxl
{

   public static void main(String args[])
   {
       String path = "F://";
       String fileName = "APIoutput.xls";
       String sheetName = "Sheet1";
       String filepath = path+"\\"+fileName;
       writeExcel(filepath,sheetName);
   }
    public static void writeExcel(String filepath, String sheetName)
   {
        //Create an object of File class to open xlsx file

        File file = new File(filepath);
        String data = "Slim Shady";

        if(!file.exists())
        {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(sheetName);
//Create a new row in current sheet
            Row row = sheet.createRow(0);
//Create a new cell in current row
            Cell cell = row.createCell(0);
//Set value to new value

            cell.setCellValue(data);
            try {
                FileOutputStream out = new FileOutputStream(new File(filepath));

                workbook.write(out);

                out.close();
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
        {
            outfile(file,filepath, sheetName, data);
        }



    }

    public static void outfile(File file, String filePath, String sheetName, String data )
    {

        //File file = new File"C:\\update.xls");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = null;
           // Cell cell = null;
            int last_row = sheet.getLastRowNum();
            row = sheet.getRow(last_row);

            System.out.println("getting cell value of 1,0:"+row.getCell(0));
            //Row newRow = sheet.createRow(last_row+1);
            int newRowCount = last_row + 1;
            Row newRow = sheet.createRow(newRowCount);
            newRow.createCell(0).setCellValue(data + " newwww");
            inputStream.close();

            try {
                FileOutputStream out = new FileOutputStream(new File(filePath), false);
             //   FileOutputStream out = new FileOutputStream(new File("E:\\suketas stuff\\Automation\\Acadia Framework\\data\\ExportExcel123.xlsx"), true);
//
                workbook.write(out);

                out.flush();
                out.close();


            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Excel written successfully..");

        }catch (Exception e) {
            e.printStackTrace();
        }



    }







}
