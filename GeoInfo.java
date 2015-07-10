/*************************************************************************
 *  
 *  GeoInfo.java monta uma tabela de simbolos a partir de um arquivo .xml
 *  criando implicitamente um formato .vll
 *  E calcula-se os minimos e maximos da latitude e longitude
 *
 *************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GeoInfo {
    
    public ST<String, Location> st;     // Tabela de símbolos 

    public GeoInfo() {                   // construtor
        st = new ST<String, Location>();
    }

    // seguintes methods calculam os minimos e maximos da latitude e longitude
    public Double findMinLat(ST<String, Location> st) {
        Location loc_ant = new Location(90, 180);
        
        for (String aux: st.keys()) {
            Location loc_prox = st.get(aux);
            if (loc_ant.latitude > loc_prox.latitude) loc_ant = st.get(aux);
        }
        return loc_ant.latitude;
    }  

    public Double findMinLongt(ST<String, Location> st) {
        Location loc_ant = new Location(90, 180);
        
        for (String aux: st.keys()) {
            Location loc_prox = st.get(aux);
            if (loc_ant.longitude > loc_prox.longitude) loc_ant = st.get(aux);
        }
        return loc_ant.longitude;
    }  

    public Double findMaxLat(ST<String, Location> st) {
        Location loc_ant = new Location(-90, -180);
        
        for (String aux: st.keys()) {
            Location loc_prox = st.get(aux);
            if (loc_ant.latitude < loc_prox.latitude) loc_ant = st.get(aux);
        }
        return loc_ant.latitude;
    }  

    public Double findMaxLongt(ST<String, Location> st) {
        Location loc_ant = new Location(-90, -180);
        
        for (String aux: st.keys()) {
            Location loc_prox = st.get(aux);
            if (loc_ant.longitude < loc_prox.longitude) loc_ant = st.get(aux);
        }
        return loc_ant.longitude;
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
                        String node     = tokens[2].replaceAll("[a-z|=|\"]+", "");
                        String lat      = tokens[9].replaceAll("[a-z|=|\"]+", "");
                        String longt    = tokens[10].replaceAll("[a-z|=|\"|/|>]+", "");
                        
                        Location loc = new Location(Double.parseDouble(lat), Double.parseDouble(longt));
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
    
}
