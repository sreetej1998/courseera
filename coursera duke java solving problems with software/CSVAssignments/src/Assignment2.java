import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
public class Assignment2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour=null;
        for(CSVRecord record:parser){
            if(coldestHour==null) coldestHour=record;
            float coldVal=Float.parseFloat(coldestHour.get("TemperatureF"));
            float recordVal=Float.parseFloat(record.get("TemperatureF"));
            if(coldVal>recordVal)
                coldestHour=record;
        }
        return coldestHour;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowHumidity=null;
        for(CSVRecord record:parser){
            if(lowHumidity==null) lowHumidity=record;
            float low=Float.parseFloat(lowHumidity.get("Humidity"));
            float curr=Float.parseFloat(record.get("Humidity"));
            if(low>curr)
                lowHumidity=record;
        }

        return lowHumidity;
    }
    public double averageTemperatureInFile(CSVParser parser){
        double sum=0.0;
        int numberOfrecords=0;
        for(CSVRecord record:parser){
            double x=Double.parseDouble(record.get("TemperatureF"));
            sum+=x;
            numberOfrecords++;
        }
        return sum/numberOfrecords;
    }
    public double  averageTemperatureWithHighHumidityInFile(CSVParser parser,int humidity){
        double sum=0.0;
        int numberOfrecords=0;
        for(CSVRecord record:parser){
            int humid=Integer.parseInt(record.get("Humidity"));
            if(humid>humidity){
                double x=Double.parseDouble(record.get("TemperatureF"));
                sum+=x;
                numberOfrecords++;}
        }
        return sum/numberOfrecords;
    }
    public void fileWithColdestTemperature(){
        DirectoryResource dir= new DirectoryResource();
        String coldFile="";
        CSVParser parserFinal=null;
        CSVRecord coldestHour=null;
        for(File f:dir.selectedFiles()){
            CSVParser parser=new FileResource(f).getCSVParser();
            CSVRecord record=coldestHourInFile(parser);
            if(coldestHour==null) coldestHour=record;
            float recordVal=Float.parseFloat(record.get("TemperatureF"));
            float currVal=Float.parseFloat(record.get("TemperatureF"));
            if(recordVal<=currVal) {
                coldestHour=record;
                coldFile=f.getName();
                parserFinal=new FileResource(f).getCSVParser();
            }
        }
        System.out.println("coldest day was in the file  "+coldFile);
        System.out.println("coldest temperature on that day was "+ coldestHour.get("TemperatureF"));
        System.out.println("All the temperatures in that file are");
        for(CSVRecord record:parserFinal){
            System.out.println(record.get("DateUTC")+"  "+record.get("TemperatureF"));
        }
    }
    public void fileWithLowestHumidity(){
        DirectoryResource dir= new DirectoryResource();
        String lowHumidFile="";
        CSVParser parserFinal=null;
        CSVRecord lowHumidty=null;
        for(File f:dir.selectedFiles()){
            CSVParser parser=new FileResource(f).getCSVParser();
            CSVRecord record=lowestHumidityInFile(parser);
            if(lowHumidty==null) lowHumidty=record;
            float recordVal=Float.parseFloat(record.get("Humidity"));
            float currVal=Float.parseFloat(record.get("Humidity"));
            if(recordVal<=currVal) {
                lowHumidty=record;
                lowHumidFile=f.getName();
                parserFinal=new FileResource(f).getCSVParser();
            }
        }
        System.out.println(lowHumidty.get("DateUTC")+"  "+lowHumidty.get("Humidity"));
    }
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    public void testFileWithLowestHumidity(){
        fileWithLowestHumidity();
    }
    public void testColdestHourInFile(){
        FileResource fr= new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord rec=coldestHourInFile(parser);
        System.out.println(rec.get("TemperatureF"));
    }
    public void testLowestHumdityInFile(){
        FileResource fr= new FileResource();
        CSVParser parser= fr.getCSVParser();
        System.out.println(lowestHumidityInFile(parser).get("Humidity"));

    }
    public void testAverageTemerature(){
        FileResource fr= new FileResource();
        CSVParser parser=fr.getCSVParser();
        System.out.println(averageTemperatureInFile(parser));
    }
   public void testAverageTemperatureWithHighHumidity(){
       FileResource fr= new FileResource();
       CSVParser parser=fr.getCSVParser();
       System.out.println(averageTemperatureWithHighHumidityInFile(parser,18));
   }
    public static void main(String args[]){
        Assignment2 a= new Assignment2();
       // a.testColdestHourInFile();
        //a.testLowestHumdityInFile();
        //System.out.println(a.averageTemperatureWithHighHumidityInFile(new FileResource().getCSVParser(),0));
        a.fileWithColdestTemperature();
    }
}
