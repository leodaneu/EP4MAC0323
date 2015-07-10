import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GeoInfo {
    
    public ST<String, Location> st;     // Tabela de símbolos 

    public GeoInfo() {                   // construtor
        st = new ST<String, Location>();
    }
/*
    public Location findMin(ST<String, Location> st) {
        Location loc = new Location(0, 0);
        
        for (String aux: st.keys()) {
            if (loc < st.get(aux)) loc = st.get(aux);
        }
        return loc;
    }    
*/
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


/*
    public Location findMax(ST<String, Location> st) {
        Location loc = new Location(0, 0);
        
        for (String aux: st.keys()) {
            if (loc > st.get(aux)) loc = st.get(aux);
        }
        return loc;
    }    
*/
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
