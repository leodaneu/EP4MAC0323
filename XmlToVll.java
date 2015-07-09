/*************************************************************************
 *  Compilation:  javac XmlToVll.java
 *  Execution:    java PXmlToVll input.osm.xml > output.vll
 *  
 *  % java XmlToVll map.osm.xml > USP.vll
 *
 *  reference on regex at 
 *  http://stackoverflow.com/questions/5946471/splitting-at-space-if-not-between-quotes
 *
 *************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class XmlToVll { 

    public static void main(String[] args) {

        String fileToParse = args[0];
        BufferedReader fileReader = null;
        try
        {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileToParse));
            // leitura linha por linha
            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if (tokens.length > 1) {

                    if (tokens[1].equals("<node")) {
                        String node     = tokens[2].replaceAll("[a-z|=|\"]+", "");;
                        String lat      = tokens[9].replaceAll("[a-z|=|\"]+", "");
                        String longt    = tokens[10].replaceAll("[a-z|=|\"|/|>]+", "");
                        
                        StdOut.println( node + " " + lat + " " + longt );
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
