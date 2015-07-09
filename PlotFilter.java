/*************************************************************************
 *  Compilation:  javac PlotFilter.java
 *  Execution:    java PlotFilter < input.txt
 *  Dependencies: StdDraw.java StdIn.java
 *  
 *  % java PlotFilter < USA.txt
 *
 *  Datafiles:    http://www.cs.princeton.edu/IntroProgramming/15inout/USA.txt
 *
 *************************************************************************/

public class PlotFilter { 

    public static void main(String[] args) {

        // read in bounding box and rescale
        /*
        double x0 = StdIn.readDouble();
        double y0 = StdIn.readDouble();
        double x1 = StdIn.readDouble();
        double y1 = StdIn.readDouble();
        */

        // run the script.
        //Process proc = Runtime.getRuntime().exec("USPnew.vll");
        // wait for the return code.
        //int ecode = proc.waitFor();

        StdOut.println("Entre com os dois pares de pontos para o enquadramento da imagem:");
        StdOut.println("Formato requerido: latitude longitude (entre com o par separado por um espaço)");
        StdOut.println("Entre com o primeiro ponto >> ");
        double x0 = StdIn.readDouble();
        double y0 = StdIn.readDouble();
        StdOut.println("Entre com o segundo ponto >> ");
        double x1 = StdIn.readDouble();
        double y1 = StdIn.readDouble();

/*
        double x0 = -46.74;
        double y0 = -23.57;

        double x1 = -46.72;
        double y1 = -23.55;

        StdDraw.setXscale(x0, x1);
        StdDraw.setYscale(y0, y1);
*/
        StdDraw.setXscale(x0, x1);
        StdDraw.setYscale(y0, y1);

        // Check how many arguments were passed in
        if(args.length == 0)
        {
            StdOut.println("Erro: inclua no argumento um arquivo .xml para sua leitura");
            System.exit(0);
        }
        else {

            In      in          = new In(args[0]);
            String  filename    = args[0];

            while (!in.isEmpty()) {

                String t0 = in.readString();
                String t1 = in.readString();
                String t2 = in.readString();

                double y = Double.parseDouble(t1);
                double x = Double.parseDouble(t2);

                //StdOut.println(x + ", " + y);
                StdDraw.point(x, y);
            }
            // display all of the points now
            // StdDraw.show(0);
        }
    

    }
}
