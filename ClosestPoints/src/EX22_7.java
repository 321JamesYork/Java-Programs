import java.util.ArrayList;
import java.util.Arrays;

/**
 * James York
 * Created Nov 11, 2017
 * Revised
 * RSU Programming2
 *
 * Description - Finds the closest set of points in a set.
 * The algorithms are implemented based on the description
 * in the text book:
 * "Introduction to Java Programming 10th edition",
 * Y.Daniel Lang, Chapter 22
 *
 */


public class EX22_7 {


    /** Define a class for a point with x- and y- coordinates */
    static class Point implements Comparable<Point> {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Compare another point object to self by checking
         * the x- coordinate and , if necessary, the y- coordinate.
         * @param p2 --an initialized Point Object.
         * @return -- 1, 0, or -1 if the point is bigger, equal, or
         * smaller respectively.
         */
        @Override
        public int compareTo(Point p2) {
            if (this.x < p2.x)
                return -1;
            else if (this.x == p2.x) {
                // Secondary order on y-coordinates
                if (this.y < p2.y)
                    return -1;
                else if (this.y == p2.y)
                    return 0;
                else
                    return 1;
            } else
                return 1;
        }

        /**
         *
         * @return the value of x and y of the object.
         */
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

    }

    /**
     * A comparator for comparing Point objects on their y-coordinates. If y-coordinates
     * are the same, compare their x-coordinate.
     */
    static class CompareY implements java.util.Comparator<Point> {

        /**                                 Compare
         * Accepts two initialized Point Objects and compares
         * the x and y members.
         * @param p1 --initialized Point Object
         * @param p2 --initialized Point Object
         * @return -- 1, 0 , -1 if Point p1 is bigger, equal, or smaller
         * respectively.
         */
        public int compare(Point p1, Point p2) {
            if (p1.y < p2.y)
                return -1;
            else if (p1.y == p2.y) {
                // Secondary order on x-coordinates
                if (p1.x < p2.x)
                    return -1;
                else if (p1.x == p2.x)
                    return 0;
                else
                    return 1;
            } else
                return 1;
        }
    }

    /**
     * This class is creates an object containing two Point Objects
     * and provides a method call to find the distance between
     * them.
     *  If no Point Objects are passed as args, will
     *  create two default Point Objects.
     */
    static class Pair {
        Point p1;
        Point p2;
        public Pair(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;
        }

        /**
         *                              getDistance
         *  Finds the distance between the two Point Objects
         *  contained in a Pair Object.
         * @return -- the distance between two Point Objects
         * as a double value.
         */
        public double getDistance(){

            return distance(p1, p2);

        }

        /**
         *						getClosestPair
         *
         * Accepts a 2D array of double values and constructs a
         * single array for processing the distance between the points
         *
         * @param points -- an initialized 2D array doubles
         * @return -- a Pair Object containing the closet set of
         * Point Objects created  from the double[][].
         ************************************************************************
         */
        public static Pair getClosestPair(double[][] points){
            System.out.println("getClosestPair double[][] invoked");
            Point[] points2 = new Point[points.length];

            for(int i = 0; i < points.length; i++)
                //double array initialized as: points[2][10]
                points2[i] = new Point(points[i][0], points [i][1]);

            return getClosestPair(points2);

        }

        /**
         *						     getClosestPair
         *
         *Accepts a single dimensional array of Point objects and sorts the
         * values then clones the sorted array and sorts based on a "Y" value.
         * Passes the sorted arrays to the distance method and returns those
         *  values.
         *
         * @param points --an initialized array of Point Objects.
         * @return -- a Pair Object containing the closest pair of Point Objects.
         ************************************************************************
         */
        public static Pair getClosestPair(Point[] points){

            System.out.println("getClosestPair Point[] invoked");
            Arrays.sort(points);
            Point[] pointsOrderedOnY = points.clone();
            Arrays.sort(pointsOrderedOnY, new CompareY());

            return distance(points, 0, points.length - 1, pointsOrderedOnY);
        }

        /**
         *						      distance
         *
         * Separates the arrays received based on the high and low values entered.
         * Creates two arrays of the same size, sorted by a mid value for the
         * arrays passed in. Checks the distance between the points and updates
         * variables each time a distance i < the previous.
         * @param pointsOrderedOnX -a sorted Point[]
         * @param low - int value -- beginning index of array.
         * @param high - int value -- ending index of array.
         * @param pointsOrderedOnY -a sorted Point[].
         * @return - a Pair Object
         ************************************************************************
         */
        public static Pair distance(Point[] pointsOrderedOnX, int low,
                                    int high, Point[] pointsOrderedOnY){
            System.out.println("Pair distance Point[] pOderOnX, low, high, Point[]"
                    + " pOrderOnY");
            if(low >=high) { // check that array has more than one element.
                return null;
            }
            else if (low + 1 == high) {     // check to see if array has more than one set.
                return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
            }
            int halfSize = (low + high) / 2;    // find middle index of array

            // find the closest pair in the lower half of array
            Pair p1 = distance(pointsOrderedOnX, low, halfSize, pointsOrderedOnY);
            // find the closest pair in the upper half of array.
            Pair p2 = distance(pointsOrderedOnX, halfSize + 1, high, pointsOrderedOnY);

            double distance;
            Pair p = null;

            if(p1 == null && p2 == null) {      // no pairs examined
                distance = Double.MAX_VALUE;
            } else if (p1 == null) {        // only 1 pair found
                distance = p2.getDistance();
                p = p2;
            } else if(p2 == null) {         // only 1 pair found.
                distance = p1.getDistance();
                p = p1;
            } else {
                // find the distance between p1 and p2 and assign to distance
                distance = Math.min(p1.getDistance(), p2.getDistance());
                // assign the smaller distance of p1 and p2 to p.
                p= ((p1.getDistance() <= p2.getDistance()) ? p1 : p2);
            }

            ArrayList<Point> stripL = new ArrayList<Point>();
            ArrayList<Point> stripR = new ArrayList<Point>();
            for (int i = 0; i < pointsOrderedOnY.length; i++){
                if ((pointsOrderedOnY[i].x <= pointsOrderedOnX[halfSize].x)
                        && (pointsOrderedOnY[i].x >= pointsOrderedOnX[halfSize].x - distance)) {
                    stripL.add(pointsOrderedOnY[i]);
                }else {
                    stripR.add(pointsOrderedOnY[i]);
                }
            }

            double d3 = distance;
            int r = 0;
            for(int i = 0; i < stripL.size(); i++){
                while(r < stripR.size() && stripL.get(i).y > stripR.get(r).y + distance){
                    r++;
                }

                int r1 = r;
                while (r1 < stripR.size() && stripR.get(r1).y <= stripL.get(i).y + distance) {
                    if (d3 > distance(stripL.get(i), stripR.get(r1))){
                        d3 = distance (stripL.get(i), stripR.get(r1));
                        p.p1 = stripL.get(i);
                        p.p2 = stripR.get(r1);
                    }
                    r1++;
                }
            }
            return p;
        }

        /**
         *
         *			                   distance()
         *
         * Accepts 2 Point Objects and gets the "x" and "y" coordinates from each
         * to pass to the next distance method.
         *
         * @param p1 -- initialized Point Object
         * @param p2 -- initialized Point Object
         * @return -- double value -- distance between p1 & p2
         ************************************************************************
         */
        public static double distance(Point p1, Point p2){
            return distance(p1.x, p1.y, p2.x, p2.y);
        }
        /**
         *
         *						     distance()
         *
         *	sets the variables for the distance formula, calculates the distance
         *	between 2 points based on their "x" and "y" coordinates
         *
         * @param x1 -- double -- p1 x- coordinate
         * @param y1 -- double -- p1 y- coordinate
         * @param x2 -- double -- p2 x- coordinate
         * @param y2 -- double -- p2 y- coordinate
         * @return -- double
         ************************************************************************
         */
        public static double distance(double x1, double y1,
                                      double x2, double y2){
            return Math.sqrt((x2 - x1) * (x2 - x1) +
                    (y2 - y1) * (y2 - y1));
        }

        public String toString() {

            return p1.toString() + p2.toString();
        }

    }

    /**
     *
     *						 main
     * Used to test and view the times of the algorithm
     *
     * @param args
     ************************************************************************
     */
    public static void main(String[] args) {


        Point[] points = new Point[10];

        long start = System.currentTimeMillis();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(Math.random() * 100, Math.random() * 100);
        }
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(Math.random() * 100, Math.random() * 100);
        }
        long stop1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();


        Pair pair = Pair.getClosestPair(points);
        long stop2 = System.currentTimeMillis();

        System.out.printf("%.5s\t%.5s\n", pair.p1.x, pair.p1.y);
        System.out.printf("%.5s\t%.5s\n", pair.p2.x, pair.p2.y);
        System.out.printf("%.5s\n",pair.getDistance());

        System.out.println("Loop completed in " + (stop1 - start) +"ms");
        System.out.println("Closest pair of " + points.length + " points " +
                "searched and computed in " + (stop2 - start2) +"ms");

        double [][] coords = new double[2][10];
        System.out.println("Filling double[][] with new random points.");
        start = System.currentTimeMillis();
        for(int i = 0; i < coords.length; i++){
            System.out.println();
            for(int j = 0; j < coords[i].length; j++){  // fill array with random numbers for points.
                coords[i][j] = Math.random() *100;
                System.out.printf("%2.3f\n",coords[i][j]);
                stop1 = System.currentTimeMillis();


            }
        }

        System.out.println("Loop completed in " + (stop1 - start) +
                "ms");
        start = System.currentTimeMillis();
        System.out.println(Pair.getClosestPair(coords).toString());
        stop1 = System.currentTimeMillis();
        System.out.println("Closest Pair with double[][] completed in " + (stop1 - start) +
                "ms");

    }

}
