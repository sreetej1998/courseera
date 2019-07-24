import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Map;

public class DNAcodon {
    private HashMap<String,Integer> map;
    public DNAcodon(){
        map= new HashMap<>();
    }
    public void buildCodonMap(int start,String dna){
        map.clear();
        for(int i=start;i<dna.length()-2;i+=3){
            String codon=dna.substring(i,i+3);
            if(map.containsKey(codon)) map.put(codon,map.get(codon)+1);
            else map.put(codon,1);
        }
    }

    public String getMostCommonCodon(){
        String codon="";
        int max=0;
        for(Map.Entry<String , Integer> entry:map.entrySet()){
            int count=(int)entry.getValue();
            if(count>max) {
                max = count;
                codon = entry.getKey();
            }
        }
        return codon;
    }

    public void printCountCodons(int start,int end){
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            int count=entry.getValue();
            if(count>start && count<end){
                System.out.println(entry.getKey()+ " : "+entry.getValue());
            }
        }
    }

    public void tester(){
        FileResource fr= new FileResource();
        String s=fr.asString().trim();
        for(int i=0;i<3;i++) {
            buildCodonMap(i, s);
            System.out.println("reading frame " + i + " has " + map.size()+" unique codons");
            System.out.println("common codon in frame "+i+" is: "+getMostCommonCodon());
            System.out.println("counts of codons between 1 and 5 are ");
            printCountCodons(1,5);
        }


    }

    public static void main(String args[]){
        DNAcodon d= new DNAcodon();
        d.tester();
    }

}
