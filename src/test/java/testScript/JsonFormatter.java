package testScript;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFormatter {
	
	
	    public static void main(String args[])
	    {
	        JSONParser parser = new JSONParser();
	        try
	        {
	            Object object = parser
	                    .parse(new FileReader("F:\\smapleJson2.json"));
	            
	            //convert Object to JSONObject
	            JSONObject jsonObject = (JSONObject)object;
	            
	            //Reading the String
	            JSONArray recallInformation = (JSONArray) jsonObject.get("recallInformation");
	            
	            //JsonPath js = new JsonPath(rawResponse.asString());
			
				 //resul = js.get(dependancyfield).toString();
	           
	            
	            for(Object recallObj:recallInformation ) 	
	            {
	            
	            
	            String vin = (String) jsonObject.get("recallInformation.vin");
	            Long model = (Long) jsonObject.get("recallInformation.model");
	            String make = (String) jsonObject.get("recallInformation.make");
	   //         int year = (int) jsonObject.get("year");
	            
	            
	            //Reading the array
	            JSONArray recalls = (JSONArray)jsonObject.get("recalls");
	            
	            //Printing all the values
	            System.out.println("Vin: " + vin);
	            System.out.println("model: " + model);
	            System.out.println("make:"+ make);
	           // System.out.println("year:"+ year);
	            
	            for(Object recallobj : recalls)
	            {
	                System.out.println(recallobj);
	            }
	            
	            
	            JSONArray serviceCampign = (JSONArray)jsonObject.get("serviceCampaigns");
	            
	            
	            for (Object ServiceCampObj:serviceCampign)
	            {
	        
	            	System.out.println(ServiceCampObj);
	            }
	            
	        }
	        }
	        
	        catch(FileNotFoundException fe)
	        {
	            fe.printStackTrace();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	}


