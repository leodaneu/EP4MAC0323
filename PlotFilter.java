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
        double x0 = 46.74;
        double y0 = 23.57;

        double x1 = 46.72;
        double y1 = 23.55;

        StdDraw.setXscale(x0, x1);
        StdDraw.setYscale(y0, y1);

        // turn on animation mode to defer displaying all of the points
        // StdDraw.show(0);

        // plot points, one at a time
        while (!StdIn.isEmpty()) {
            double ignore = StdIn.readDouble();
            double y = StdIn.readDouble();
            y = y * (-1);
            //x = ( x * (-1) ) - 23;
            //x = ( x + 23 ) * (- 1);
            double x = StdIn.readDouble();
            x = x * (-1);
            //y = ( y + 46 ) * (- 1);
            StdOut.println(x + " " + y);
            StdDraw.point(x, y);
        }

        // display all of the points now
        // StdDraw.show(0);

    }
}
