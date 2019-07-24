import org.apache.commons.csv.CSVParser;
import edu.duke.*;
import org.apache.commons.csv.CSVRecord;
import java.util.Arrays;
import java.util.List;

public class Assignment1 {

    public FileResource fr;
    public void countryInfo(CSVParser parser,String country){
    boolean notFount=true;
    for(CSVRecord record:parser){
        if(record.get("Country").equals(country)) {
            System.out.println(record.get("Country") + " : " + record.get("Exports")+" : "+record.get("Value (dollars)"));
            notFount=false;
        }
    }
    if(notFount) System.out.println("NOT FOUND");
    }

    public void listExportersTwoProducts(CSVParser parser,String export1,String export2){
        parser= fr.getCSVParser();

        for(CSVRecord record:parser){
            List<String> exports = Arrays.asList(record.get("Exports").split(", "));
            if(exports.contains(export1) && exports.contains(export2))
                System.out.println(record.get("Country"));
            }
        }

        public int numberOfExporters(CSVParser parser,String export){
            parser= fr.getCSVParser();
            int countExporters=0;
        for(CSVRecord record:parser){
            List<String> exports = Arrays.asList(record.get("Exports").split(", "));
            if(exports.contains(export))
                countExporters++;
        }
        return countExporters;
        }

        public void bigExporters(CSVParser parser,String amount){
            parser= fr.getCSVParser();
        for(CSVRecord record:parser){
            if(amount.length()<record.get("Value (dollars)").length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
        }


    public void tester(){
        fr= new FileResource();
        CSVParser parser= fr.getCSVParser();
        countryInfo(parser,"Germany");
        listExportersTwoProducts(parser,"gold","diamonds");
        bigExporters(parser,"$400,000,000");
        System.out.println(numberOfExporters(parser,"gold"));
    }

    public static void main(String arg[]){
        Assignment1 a= new Assignment1();
        a.tester();
    }
}
