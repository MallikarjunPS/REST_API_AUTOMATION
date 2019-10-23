package testScript;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import resources.Excel;

import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class apiHandler 
{
	 
	int k=1;
	int rowCount =0 ;
	int apiIdSheet1 = 0;
	String xTokenValue = "" ;
	int  i;
	int j;
	int apiIdSheet2 =0 ;
	int lastColumnNumber = 0;
	static String resul;
	//test tast
	
	public String dependantAPI(int dId, int tId,String xlpath,String sheetname,String xlpathCases, String sheetname2, int apiID) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		
			lastColumnNumber = Excel.lastColumnNumber(xlpath, sheetname, dId);
			
			//int l = lastColumnNumber -7;
			
			
				
				
			String dependancyfield = Excel.getCellValue(xlpath, sheetname, apiID, 8);
			String param = Excel.getCellValue(xlpathCases, sheetname2, tId,3 );
			
			Response rawResponse =	given().
			header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
			
			when().
			get(Excel.getCellValue(xlpath, sheetname, dId, 4),param).
			then().extract().response();
			
		
			
			//System.out.println(rawResponse.asString());
			
			JsonPath js = new JsonPath(rawResponse.asString());
			
			
			//System.out.println(dependancyfield);
			 resul = js.get(dependancyfield).toString();
			
			
			//System.out.println("Resul = "+resul);
			
			
			return resul;
			
		}

	public  String dependantAPI2 (int dId, int tId,String xlpathApi,String sheetname,String xlpathApiTestcase, String Output, int apiID) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		String dependancyfield = Excel.getCellValue(xlpathApi, sheetname, apiID, 8);
		FileInputStream fs=new FileInputStream(xlpathApiTestcase);
		Workbook wb= WorkbookFactory.create(fs);
		int rowcn = Excel.getRowCount("C:\\Users\\CTPL\\Desktop\\APIOutputSucess.xlsx", "Output");
		
		for (int i=1; rowcn>= i; i++)
		{	
				int tcid = Integer.parseInt(Excel.getCellValue("C:\\Users\\CTPL\\Desktop\\APIOutputSucess.xlsx", "Output", i, 1));
				
				 if (tcid == tId)
				 {
					 String storedResponse = Excel.getCellValue("C:\\Users\\CTPL\\Desktop\\APIOutputSucess.xlsx", "Output", i, 4);
					 
					 JsonPath js = new JsonPath(storedResponse);
						
						
						//System.out.println(dependancyfield);
						 resul = js.get(dependancyfield).toString();
						 
						
					 
				 }
				
		
		}
	
		
		 return resul;
	
	}


		
	}


