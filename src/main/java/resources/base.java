package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



//import org.apache.logging.log4j.core.util.FileUtils;

public class base {
	
	 Properties prop=new Properties();
	
	
	public String getbaseUrl() throws IOException
	
	{
		FileInputStream fis=new FileInputStream("F:\\Project\\REST assured automation\\src\\resource\\data.properties");
		prop.load(fis);
		String base=prop.getProperty("baseUrl");
		
		return base;
		
	}
	
public String getXtoken() throws IOException
	
	{
		FileInputStream fis=new FileInputStream("F:\\Project\\REST assured automation\\src\\resource\\data.properties");
		prop.load(fis);
		String token=prop.getProperty("xToken");
		
		return token;
		
	}
	
	
	//prop.get("HOST");
	}