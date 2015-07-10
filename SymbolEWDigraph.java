/*************************************************************************
 *  Compilation:  javac SymbolEWDigraph.java
 *  Execution:    java SymbolEWDigraph
 *  Dependencies: ST.java EWDigraph.java In.java
 *  
 *  %  java SymbolEWDigraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  ATL
 *     HOU
 *     MCO
 *  LAX
 *
 *************************************************************************/

public class SymbolEWDigraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private EdgeWeightedDigraph G;

    public SymbolEWDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an aray
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the digraph by connecting first vertex on each
        // line to all others
        G = new EdgeWeightedDigraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                G.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public EdgeWeightedDigraph G() {
        return G;
    }

    public static void main(String[] args) {
        In in = new In(args[0]); 
        String filename  = args[0];
        String delimiter = args[1];
        SymbolEWDigraph sg = new SymbolEWDigraph(filename, delimiter);
        EdgeWeightedDigraph G = sg.G();
        while (!in.isEmpty()) {
            String t = in.readString();
            StdOut.println(t);
            for (int v : G.adj(sg.index(t))) {
                StdOut.println("   " + sg.name(v));
            }
        }
    }
}
