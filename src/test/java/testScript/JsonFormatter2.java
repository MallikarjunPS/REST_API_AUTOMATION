package testScript;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFormatter2 {


public static void main(String [] args) {
    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
    JSONObject obj;
    // The name of the file to open.
    String fileName = "F:\\\\response_1560319340987.json";

    // This will reference one line at a time
    String line = null;

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            obj = (JSONObject) new JSONParser().parse(line);
            json.add(obj);
            System.out.println((String)obj.get("vin")+":"+
                               (String)obj.get("model"));
        }
        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println("Error reading file '" + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}

