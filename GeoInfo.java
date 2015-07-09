import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GeoInfo {
    
    ST<String, Location> st;     // Tabela de símbolos 

    public GeoInfo() {           // construtor
        ST<String, Location> st = new ST<String, Location>();
    }

    public void runScript(String fileToParse, ST<String, Location> st) {
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
                        
                        Location loc = new Location(Double.parseTo(lat), Double.parseTo(longt));
                        st.put(node, loc);
                        
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
    
    //public static void main(String[] args) {
    //    ST<> st
    //    String fileToParse = args[0];
    //    runScript(fileToParse, st);
    //}    
}
