package read.xpath.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperty {

    String value="";

    public String getValueOfKey(String Key){
        try {
            File file = new File("Config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            value = properties.getProperty(Key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
