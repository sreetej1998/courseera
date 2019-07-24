import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

public class PerimeterAssignmentRunner {

    public FileResource fr;
    public ArrayList<File> dir;


    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numberOfPoints=0;
        for(Point currPt:s.getPoints()){
            numberOfPoints++;
        }
        return numberOfPoints;
    }

    public double getAverageLength(Shape s) {

        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        Point lastPt=s.getLastPoint();
        double maxDistance=0;
        //calculating largest side
        for(Point currPt:s.getPoints()){
            double currDistance=lastPt.distance(currPt);
            if(maxDistance< currDistance)  maxDistance=currDistance;
            lastPt=currPt;

        }

        return maxDistance;
    }

    public double getLargestX(Shape s) {
        double maxX=s.getLastPoint().getX();
        for(Point currPt:s.getPoints()){
            if(currPt.getX()>maxX)
                maxX=currPt.getX();
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() throws Exception {
        storeFileNames();
        double largestPerimeter=0;
        for(File file:dir){
            Shape shape=new Shape(new FileResource(file));
            double currPerimeter=getPerimeter(shape);
            if(currPerimeter>largestPerimeter)
                largestPerimeter=currPerimeter;
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() throws Exception {
        storeFileNames();
        String maxPerimeter="";
        double largestPerimeter=0;
        for(File file:dir){
            String name=file.getName();
            Shape shape=new Shape(new FileResource(file));
            double currPerimeter=getPerimeter(shape);
            if(currPerimeter>largestPerimeter){
                largestPerimeter=currPerimeter;
                maxPerimeter=name;
            }
        }
        return maxPerimeter;
    }

    public void testPerimeter () {
        fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter         :           " + length);
        System.out.println("average length    :           "+getAverageLength(s));
        System.out.println("largest side      :          "+ getLargestSide(s));
        System.out.println("largest X         :          "+ getLargestX(s));
        System.out.println("number of points  :          "+ getNumPoints(s));
    }

    public void testPerimeterMultipleFiles() throws  Exception {

        System.out.println("largest perimeter :           " +getLargestPerimeterMultipleFiles());

        // Put code here
    }

    public void testFileWithLargestPerimeter() throws  Exception {

        System.out.println("file with largest perimeter is:  "+getFileWithLargestPerimeter());

    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void storeFileNames() throws Exception {
        DirectoryResource dr = new DirectoryResource();
        dir= new ArrayList<File>();
        for (File f : dr.selectedFiles()) {
            dir.add(f);

        }

    }

    public static void main (String[] args)
    {
        try{
            PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
            pr.testPerimeter();
            pr.testPerimeterMultipleFiles();
            pr.testFileWithLargestPerimeter();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}