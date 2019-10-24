package resources;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;


public class Excel
{

    private static final String FILE_NAME = "C:\\Users\\CTPL\\Desktop\\APIOutputSucess.xlsx";
    private static final String FILE_NAME2 = "C:\\Users\\CTPL\\Desktop\\APIOutputFail.xlsx";
    static File targetFile = new File (FILE_NAME);
    static FileInputStream fs;
    static  int  lastrownumber;
    static  int lastColumnnumber;
    static TreeMap<String, Object[]> data = new TreeMap<String, Object[]>();

    public static  String getCellValue(String xlPath,String sheet,int row, int cell)
    {
        String v="";


        try
        {
            FileInputStream fs=new FileInputStream(xlPath);
            Workbook wb= WorkbookFactory.create(fs);
            Cell  c=wb.getSheet(sheet).getRow(row).getCell(cell);


                if(c.getCellTypeEnum()==CellType.STRING)
                {
                    v= c.toString();
                }

                else
                {
                    long n=(long)c.getNumericCellValue();
                    v=String.valueOf(n);
                }


        }

        catch (EncryptedDocumentException e)
        {
          System.out.println("Given Document is encrypted");
        }



        catch (IOException e)
        {
            System.out.println("Please provide the valid path for Excel");
        }

        catch (NullPointerException e)
        {
            System.out.println("Test Run Complete");
        }

        return v;
   }


    public static int getRowCount(String xlPath,String sheet)
    {
        int rc=0;
        try
        {
            FileInputStream fs = new FileInputStream(xlPath);
            Workbook wb=WorkbookFactory.create(fs);
            rc=wb.getSheet(sheet).getLastRowNum();
        }

        catch (Exception e)
        {

            e.printStackTrace();
        }
        return rc;
    }


    public static void writeOutputSucces (String baseUrl, String token, int apiID, String actualRes, String expectRes, int tcid) throws EncryptedDocumentException, InvalidFormatException, IOException
    {
          //Create an object of File class to open xlsx file


            File file = new File(FILE_NAME);


            if(!file.exists())
            {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Output");
                data.put("1", new Object[]{ "ApiID","TcID", "BaseURL", "Token","ActualRes","ExpectRes" });
                data.put("2", new Object[]{ apiID,tcid,baseUrl,token,actualRes,expectRes});
    //Create a new row in current sheet
                //Row row = sheet.createRow(0);
    //Create a new cell in current row
                //Cell cell = row.createCell(0);
    //Set value to new value

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
                    FileOutputStream out = new FileOutputStream(new File(FILE_NAME));

                    workbook.write(out);

                    out.close();
                    workbook.close();
                    System.out.println("Excel written successfully..");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //cell.setCellValue(data);

            }
            else
            {
                outfile(file,FILE_NAME, "Output",baseUrl,token,apiID,actualRes,expectRes,tcid);
            }



        }



    public static void writeOutputFailure (String baseUrl3, String token3, int apiID3, String actualRes3, String output3, int tcid3, String expectRes3)
    {


        File file = new File(FILE_NAME2);


        if(!file.exists())
        {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Output");
            data.put("1", new Object[]{ "ApiID","TcID", "BaseURL", "Token","ActualRes","Output","expectedRes" });
            data.put("2", new Object[]{ apiID3,tcid3,baseUrl3,token3,actualRes3,output3,expectRes3});
//Create a new row in current sheet
            //Row row = sheet.createRow(0);
//Create a new cell in current row
            //Cell cell = row.createCell(0);
//Set value to new value

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
                FileOutputStream out = new FileOutputStream(new File(FILE_NAME2));

                workbook.write(out);

                out.close();
                workbook.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //cell.setCellValue(data);

        }
        else
        {
            outfile2(file,FILE_NAME2, "Output",baseUrl3,token3,apiID3,actualRes3,output3,tcid3,expectRes3);
        }

        }


    public static void outfile(File file, String filePath, String sheetName,String baseUrl1, String token1, int apiID1, String actualRes1, String expectRes1, int tcid1)
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
                data.put("1", new Object[]{ apiID1,tcid1,baseUrl1,token1,actualRes1,expectRes1});

                Set<String> keyset = data.keySet();
                int newRowCount = last_row + 1;



                for (String key : keyset) {
                    // this creates a new row in the sheet
                     Row newRow = sheet.createRow(newRowCount++);
                    Object[] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr) {
                        // this line creates a cell in the next column of that row
                        Cell cell = newRow.createCell(cellnum++);
                        if (obj instanceof String)
                            cell.setCellValue((String)obj);
                        else if (obj instanceof Integer)
                            cell.setCellValue((Integer)obj);
                    }
                }
                inputStream.close();


                try {
                    FileOutputStream out = new FileOutputStream(new File(filePath), false);
                 //   FileOutputStream out = new FileOutputStream(new File("E:\\suketas stuff\\Automation\\Acadia Framework\\data\\ExportExcel123.xlsx"), true);
    //
                    workbook.write(out);

                    out.flush();
                    workbook.close();


                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Excel written successfully..");

            }
            catch (Exception e) {
                e.printStackTrace();
            }
                //Row newRow = sheet.createRow(newRowCount);
            //newRow.createCell(0).setCellValue(data + " newwww");


        }


    public static void outfile2(File file2, String filePath2, String sheetName2,String baseUrl2, String token2, int apiID2, String actualRes2, String output2, int tcid2, String expectRes2)
    {

        //File file = new File"C:\\update.xls");
        try {
            FileInputStream inputStream = new FileInputStream(file2);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName2);
            Row row = null;
           // Cell cell = null;
            int last_row = sheet.getLastRowNum();
            row = sheet.getRow(last_row);

            System.out.println("getting cell value of 1,0:"+row.getCell(0));
            //Row newRow = sheet.createRow(last_row+1);
            data.put("1", new Object[]{ apiID2,tcid2,baseUrl2,token2,actualRes2,output2,expectRes2});

            Set<String> keyset = data.keySet();
            int newRowCount = last_row + 1;



            for (String key : keyset) {
                // this creates a new row in the sheet
                 Row newRow = sheet.createRow(newRowCount++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    // this line creates a cell in the next column of that row
                    Cell cell = newRow.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }

            inputStream.close();


            try {
                FileOutputStream out = new FileOutputStream(new File(filePath2), false);
             //   FileOutputStream out = new FileOutputStream(new File("E:\\suketas stuff\\Automation\\Acadia Framework\\data\\ExportExcel123.xlsx"), true);
//
                workbook.write(out);

                out.flush();
                workbook.close();


            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Excel written successfully..");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
            //Row newRow = sheet.createRow(newRowCount);
        //newRow.createCell(0).setCellValue(data + " newwww");


    }


    public static int lastColumnNumber(String xlpath, String sheet, int row) throws EncryptedDocumentException, IOException
    {
        FileInputStream fs=new FileInputStream(xlpath);
        Workbook wb= WorkbookFactory.create(fs);

        Sheet sheet1 = wb.getSheet(sheet);

        Row r = sheet1.getRow(row);

        lastColumnnumber = r.getLastCellNum();
        return lastColumnnumber;

    }


}





	

   
  
   


