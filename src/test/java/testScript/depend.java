package testScript;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.ui.RefineryUtilities;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.Excel;
import resources.PieChart_AWT;

import java.io.IOException;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
	
public class depend {
		
		String xlpath = "";
		String sheetname = "";
		String xlpathCases = "";
		String sheetname2 = "";
		int k=1;
		int rowCount =0 ;
		int apiIdSheet1 = 0;
		String xTokenValue = "" ;
		int  i;
		int j;
		int apiIdSheet2 =0;
		double passTestCases=0;
		double failTestCases=0;
		double skipTestCases=0;
		int success =0;

		String data;		
		apiHandler t4 = new apiHandler();
		
		@BeforeTest
		public void beforeAPI()
			{ 	
				Scanner sc = new Scanner (System.in);
				
			
				System.out.println("Provide path for excel page");
				xlpath = sc.next();
			
				System.out.println("Provide sheet name");
				sheetname = sc.next();
			
				System.out.println("Provide path for excel page for cases");
				xlpathCases = sc.next();
			
				System.out.println("Provide sheet name");
				sheetname2 = sc.next();
			
			
				rowCount = Excel.getRowCount(xlpath, sheetname);
		
				System.out.println("Row count in given Excel is "+rowCount);		
				
				sc.close();	
			}
		
		
			@Test
		public void allAPI() throws EncryptedDocumentException, InvalidFormatException, IOException  
		
				{ 		
				
						
					for (i=1; rowCount>= i; i++)
						
						{
					
							String methodType = Excel.getCellValue(xlpath, sheetname, i, 0);
							String methodInputType = Excel.getCellValue(xlpath, sheetname, i, 1);
				
							RestAssured.baseURI= Excel.getCellValue(xlpath, sheetname, i, 2); 
							xTokenValue = Excel.getCellValue(xlpath, sheetname, i, 3);
							apiIdSheet1 = Integer.parseInt(Excel.getCellValue(xlpath, sheetname, i, 5));
							System.out.println(apiIdSheet1);
						
							
						
							if (methodType.equalsIgnoreCase("GET") && methodInputType.equalsIgnoreCase("Only Parameter"))
								{
									 j=0;
								
									while (j==0)
										{
											testGetApi();
										}					
								
								}
				
							
						    else if (methodType.equalsIgnoreCase("POST")&& methodInputType.equalsIgnoreCase("Only body"))
									{ 
										 j=0;
								
											while (j==0)
												{
												
													testPostApi();
												}
									}
								
						    else if (methodType.equalsIgnoreCase("POST") && methodInputType.equalsIgnoreCase("Only parameter"))
							
						    {
						    	
						   	 	j=0;
								
						   	 		while (j==0)
									{
									
										testPostApi();
									}
						    	
						    }
							
						    else if(methodType.equalsIgnoreCase("POST") && methodInputType.equalsIgnoreCase("multi parameter"))  
						    		{
						    			j=0;
								
						    			while (j==0)
						    			{
								
						    				testPostApi();
						    			}
						    	
						    		}
							
							
						    else if(methodType.equalsIgnoreCase("PUT") && methodInputType.equalsIgnoreCase("Body and Parameter"))  
				    		{
				    			j=0;
						
				    			while (j==0)
				    			{
						
				    				testPutApi();
				    			}
				    	
				    		}
					
					
	
							
						    else if(methodType.equalsIgnoreCase("PUT") && methodInputType.equalsIgnoreCase("Only Body"))  
				    		{
				    			j=0;
						
				    			while (j==0)
				    			{
						
				    				testPutApi();
				    			}
				    	
				    		}
					
							
							else
								{
									 j=0;
									
									while (j==0)
									{
										
										testPutApi();	
									}
									
								}
						
						}
					
					
					try {
						afterAllAPI();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}


		public void testPutApi() throws EncryptedDocumentException, InvalidFormatException, IOException 
			{
				
				String  stringDependencyID = Excel.getCellValue(xlpath, sheetname, i, 6);
				String  stringTestCaseDependencyID = Excel.getCellValue(xlpath, sheetname, i, 7);
				String  stringTestCaseID = Excel.getCellValue(xlpathCases, sheetname2, k, 1);
				
			
				int dependencyID = Integer.parseInt(stringDependencyID);
				int testcasedependenyID = Integer.parseInt(stringTestCaseDependencyID);
				int testcaseID = Integer.parseInt(stringTestCaseID);
				
				
					
	if (dependencyID != -1 && testcasedependenyID != -1 && apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
					{		
		
						data = t4.dependantAPI2(dependencyID,testcasedependenyID,xlpath,sheetname,xlpathCases,sheetname2,apiIdSheet1);
						
						try
						{
						 	apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
						}
						
					catch (Exception e)
						{
							System.out.println("All testcases are executed");
							
						}
		
						if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
							{	
								String param = data;
								
				
								Response rawResponse =	given().
								header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
								body(Excel.getCellValue(xlpathCases, sheetname2, k, 5)).
							
								when().
								put(Excel.getCellValue(xlpath, sheetname, i, 4),param).
								then().extract().response();
						
		
								String actualResponse = rawResponse.asString();
						
								String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
						
						
								k++;
							
							try 	
								{
									JSONAssert.assertEquals(expectedResponse, actualResponse, true);
								}
							
							catch (Exception e)
								{	
									failTestCases++;
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									testPutApi();
								}	
							
							catch (AssertionError e)
								{	
									failTestCases++;
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(Outputexception);
									testPutApi();
								}
						
							passTestCases++;
							Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);
							
						
					 		}
						
						else 
						{
							j=1;
						
						}
					}
					
					
					else
					{
						
						try
						{
						 	apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
						}
						
					catch (Exception e)
						{
							System.out.println("All testcases are executed");
							
						}
						
						
						if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
						{	
							String param = Excel.getCellValue(xlpathCases, sheetname2, k, 3);
					
							Response rawResponse =	given().
							header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
							body(Excel.getCellValue(xlpathCases, sheetname2, k, 5)).
						
							when().
							put(Excel.getCellValue(xlpath, sheetname, i, 4),param).
							then().extract().response();
							

							String actualResponse = rawResponse.asString();
							
							String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
							
							
							k++;
								
								try 	
									{
										JSONAssert.assertEquals(expectedResponse, actualResponse, true);
									}
								
								catch (Exception e)
									{	
										failTestCases++;
										String Outputexception = e.toString();
										
										Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
										testPutApi();
									}	
								
								catch (AssertionError e)
									{	
										failTestCases++;
										String Outputexception = e.toString();
										Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
										System.out.println(Outputexception);
										testPutApi();
									}
								
								passTestCases++;
								Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);
							
						 		}
							
							else 
							{
								j=1;
							
							}
					}
	}				


		public void testPostApi() throws EncryptedDocumentException, InvalidFormatException, IOException 
			{
				String  stringDependencyID = Excel.getCellValue(xlpath, sheetname, i, 6);
				String  stringTestCaseDependencyID = Excel.getCellValue(xlpath, sheetname, i, 7);
				String  stringTestCaseID = Excel.getCellValue(xlpathCases, sheetname2, k, 1);
				
				int dependencyID = Integer.parseInt(stringDependencyID);
				int testcasedependenyID = Integer.parseInt(stringTestCaseDependencyID);
				int testcaseID = Integer.parseInt(stringTestCaseID); 


				if (dependencyID != -1 && testcasedependenyID != -1)
				{	
					
					
					
					data = t4.dependantAPI(dependencyID,testcasedependenyID,xlpath,sheetname,xlpathCases,sheetname2,apiIdSheet1);
				
				try
					{
						apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
					}
		
				catch (Exception e)
					{
						System.out.println("All testcases are executed");
					}


				if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
				
				{	
					
					String paramn = data;
						
				
						Response rawResponse =	given().
						header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
						body(Excel.getCellValue(xlpathCases, sheetname2, k, 5)).
					
						when().
						post(Excel.getCellValue(xlpath, sheetname, i, 4),paramn).
						then().extract().response();
						

						String actualResponse = rawResponse.asString();
						
						String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
						
						
						k++;
							
							try 	
								{
									JSONAssert.assertEquals(expectedResponse, actualResponse, true);
								}
							
							catch (Exception e)
								{	
									failTestCases++;
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(Outputexception);
									testPostApi();
								}	
							catch (AssertionError a)
								{	
									failTestCases++;
									String Outputexception = a.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(Outputexception);
									testPostApi();
								}
						
							passTestCases++;
							Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);
							
					}

			
		else 
			{
				j=1;
		
			}


		}
				
				
				else 
				{
					
					
					
					try
					{
						apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
					}
		
				catch (Exception e)
					{
						System.out.println("All testcases are executed");
					}


				if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
		
				{
					
				
						Response rawResponse =	given().
						header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
						body(Excel.getCellValue(xlpathCases, sheetname2, k, 5)).
					
						when().
						post(Excel.getCellValue(xlpath, sheetname, i, 4)).
						then().extract().response();
						

						String actualResponse = rawResponse.asString();
						
						String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
						
						
						k++;
							
							try 	
								{
									JSONAssert.assertEquals(expectedResponse, actualResponse, true);
								}
							
							catch (Exception e)
								{	
									failTestCases++;
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(Outputexception);
									testPostApi();
								}	
							catch (AssertionError a)
								{	
									failTestCases++;
									String Outputexception = a.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(Outputexception);
									testPostApi();
								}
							
							passTestCases++;
							Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);
							
					}

			
		
		else 
			{
				j=1;
		
			}

					
					
					
					
		}
				
	}


		public void testGetApi() throws EncryptedDocumentException, InvalidFormatException, IOException 
				{
					String  stringDependencyID = Excel.getCellValue(xlpath, sheetname, i, 6);
					String  stringTestCaseDependencyID = Excel.getCellValue(xlpath, sheetname, i, 7);
					String  stringTestCaseID = Excel.getCellValue(xlpathCases, sheetname2, k, 1);
					success = 0;
					int dependencyID = Integer.parseInt(stringDependencyID);
					int testcasedependenyID = Integer.parseInt(stringTestCaseDependencyID);
					int testcaseID = Integer.parseInt(stringTestCaseID);
					
			 


			if (dependencyID != -1 && testcasedependenyID != -1 && apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
			{	

				data = t4.dependantAPI(dependencyID,testcasedependenyID,xlpath,sheetname,xlpathCases,sheetname2,apiIdSheet1);
			
				try
				{
					apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
				}
	
			catch (Exception e)
				{
					System.out.println("All testcases are executed");
				}
	


			if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
			
			{	
				
				String paramn = data;
					
			
					Response rawResponse =	given().
					header("AccessToken",xTokenValue).contentType("application/json").header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").
					body(Excel.getCellValue(xlpathCases, sheetname2, k, 5)).
				
					when().
					get(Excel.getCellValue(xlpath, sheetname, i, 4),paramn).
					then().extract().response();
					

					String actualResponse = rawResponse.asString();
					
					String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
					
					
					k++;
						
						try 	
							{
								JSONAssert.assertEquals(expectedResponse, actualResponse, true);
							}
						
						catch (Exception e)
							{	failTestCases++;
								String Outputexception = e.toString();
								Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
								System.out.println(Outputexception);
								testGetApi();
							}	
						catch (AssertionError a)
							{	failTestCases++;
								String Outputexception = a.toString();
								Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
								System.out.println(Outputexception);
								testGetApi();
							}
						
						passTestCases++;
						Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);		
					
					
				}

		
	else 
		{
			j=1;
	
		}



			
			}
			
			
			else
			{
			
				
				try
					{
						apiIdSheet2 = Integer.parseInt(Excel.getCellValue(xlpathCases, sheetname2, k, 0));
					}
		
				catch (Exception e)
					{
						System.out.println("All testcases are executed");
					}

		
				if (apiIdSheet1 == apiIdSheet2 && apiIdSheet2 != -1)
					{	
						String param = Excel.getCellValue(xlpathCases, sheetname2, k, 3);
					
		
						Response rawResponse= given().
						header("x-token",xTokenValue).header("ApplicationKey","301C01F6-450E-4D97-B522-CD69EF25B574").

						when().
				    	get(Excel.getCellValue(xlpath, sheetname, i, 4),param).
				    	then().extract().response();
						
						
						String actualResponse = rawResponse.asString();
						
						String expectedResponse = Excel.getCellValue(xlpathCases, sheetname2, k, 4);
						
						k++;
							
							try 	
								{
									JSONAssert.assertEquals(expectedResponse, actualResponse, true);
								}
							
							catch (AssertionError e)
								{	
									success= 1;
									failTestCases++;
									System.out.println(success);
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									testGetApi();
									
								}	
							
							catch (Exception e)
								{	
								
								    success= 1;
									failTestCases++;
									System.out.println(success);
									String Outputexception = e.toString();
									Excel.writeOutputFailure(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, Outputexception,testcaseID, expectedResponse);
									System.out.println(e);
									testGetApi();
								}
					
							if (success==0)
							{
								passTestCases++;
								Excel.writeOutputSucces(RestAssured.baseURI, xTokenValue, apiIdSheet1, actualResponse, expectedResponse,testcaseID);
								System.out.println(success);
							}
					    
					}
		
		else 
			{
				j=1;
			}
				
				
		}
			
	}		

		
		public void afterAllAPI() throws InterruptedException
		{		
			
			  PieChart_AWT demo = new PieChart_AWT( "API TEST REPORT",passTestCases,failTestCases,skipTestCases);  
		      demo.setSize( 560 , 367 );    
		      RefineryUtilities.centerFrameOnScreen( demo );    
		      demo.setVisible( true );
		     // Thread.sleep(1000000);
		      System.out.println("after all API");
			  
			
		}


}		
			


