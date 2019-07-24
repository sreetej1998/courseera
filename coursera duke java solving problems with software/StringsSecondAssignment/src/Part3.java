import java.util.ArrayList;

public class Part3 {
public int count(String dna){
        ArrayList<String> genes= new ArrayList<String>();
        int start=0;
        String gene="";
        while(start<dna.length()-3){
            String s=dna.substring(start,start+3);
            if(s.equals("AGT")){
                while(start<dna.length()-3 && !(dna.substring(start,start+3).equals("TTA")) ){
                    gene+=Character.toString(dna.charAt(start));
                    start+=1;
                }
                if((dna.substring(start,start+3).equals("TTA"))){
                gene+="TTA";
                genes.add(gene);}
                gene="";
            }
            else start+=1;
        }

        return genes.size();

    }

    public static void main(String args[]){
    Part3 pt= new Part3();
    System.out.println(pt.count("AGTASDFASDFASDFTTAAGTASDFTTA"));
    }
}
