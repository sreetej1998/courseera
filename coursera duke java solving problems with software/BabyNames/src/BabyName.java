import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyName {

    public void totalBirths(){
    int boyCount=0,girlCount=0;
    CSVParser  parser= new FileResource().getCSVParser(false);
    for(CSVRecord record:parser){
        if(record.get(1).equals("M")) boyCount++;
        else girlCount++;
    }
    System.out.println("Total births are: "+boyCount+girlCount+"boys are"+boyCount+"girls are:"+girlCount);
    }

    public int getRank(int year,String name,String gender){
        String nameOfFile="yob"+year+"short.csv";
        CSVParser parser= new FileResource(nameOfFile).getCSVParser(false);
        int count=1;
        for(CSVRecord record:parser){
            if(!record.get(1).equals(gender)) continue;
            if(name.equals(record.get(0))) return count;
            count++;

        }
        return -1;
    }

    public String getName(int year,int rank,String gender){
        String fileName="yob"+year+"short.csv";
        CSVParser parser= new FileResource(fileName).getCSVParser(false);
        int count=1;
        for(CSVRecord record:parser){
            if(!gender.equals(record.get(1))) continue;
            if(count==rank) return record.get(0);
            count++;
        }
        return "NO NAME";
    }

    public String whatIsMyName(String name,int year,int newYear,String gender){
        int rankInCurrYear=getRank(year,name,gender);
        System.out.println(rankInCurrYear);
        String nameOfNewYear=getName(newYear,rankInCurrYear,gender);
        System.out.println(nameOfNewYear);
        return nameOfNewYear;
    }

    int yearOfHighestRanking(String name,String gender){
        DirectoryResource resource = new DirectoryResource();
        int toprank=Integer.MAX_VALUE,topyear=0;
        for(File f:resource.selectedFiles()){
         int year=Integer.parseInt(f.getName().substring(3,7));
         int rank=getRank(year,name,gender);
         if(rank<toprank){
             toprank=rank;
            topyear=year;
         }
        }
        return topyear;
    }

    double getAverageRank(String name,String gender){
        DirectoryResource resource= new DirectoryResource();
        int rankSum=0,count=0;
        for(File f:resource.selectedFiles()){
            int year=Integer.parseInt(f.getName().substring(3,7));
            int rank=getRank(year,name,gender);
            if(rank!=-1) {
                rankSum += rank;
                count++;
            }
        }
        return (double)rankSum/count;
    }

    public int getTotalBirthsRankedHigher(int year, String name,String gender){
        String fileName="yob"+year+"short.csv";
        CSVParser parser= new FileResource(fileName).getCSVParser(false);
        int rank=getRank(year,name,gender);
        int count=1,total=0;
        for(CSVRecord record:parser){
            if(!record.get(1).equals(gender)) continue;
            if(count<rank)
                total+=Integer.parseInt(record.get(2));
            count++;
        }

        return  total;

    }

    public static void main(String []args){
        BabyName bn= new BabyName();
        //System.out.println(bn.getName(2012,5,"F"));
        //System.out.println(bn.whatIsMyName("Ava",2012,2013,"F"));
        //System.out.println(bn.yearOfHighestRanking("Olivia","F"));
       // System.out.println(bn.getAverageRank("Noah","M"));
        System.out.println(bn.getTotalBirthsRankedHigher(2012,"Isabella","F"));
    }


}
