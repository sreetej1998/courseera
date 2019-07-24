import java.util.HashSet;

public class Part1 {
    public static HashSet<String> stopCodons;
    public Part1(){
        stopCodons= new HashSet<>();
        stopCodons.add("TTA");
        stopCodons.add("TAG");
        stopCodons.add("TGA");
    }
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        String genes=dna.substring(startIndex,dna.length());
        int ind=0;
        if(!genes.contains(stopCodon)) return dna.length()+1;
        int start=3;
        while(start<=genes.length()-3){
            String temp=genes.substring(start,start+3);
            if(stopCodon.equals(temp) && start%3==0){
                return start+startIndex+3;
            }
            start+=1;
        }
        return dna.length()+1;
    }

    public String findGene(String dna){

        int a=findStopCodon(dna,dna.indexOf("AGT"),"TTA");
        int b=findStopCodon(dna,dna.indexOf("AGT"),"TAG");
        int c=findStopCodon(dna,dna.indexOf("AGT"),"TGA");

        if(a<b && a<c) return dna.substring(dna.indexOf("AGT"),a);
        else if(b<a && b<c) return dna.substring(dna.indexOf("AGT"),b);
        else if(c<a && c<b) return dna.substring(dna.indexOf("AGT"),c);
        else return "";

    }

    public static String lastPart(String stringa,String stringb){
        int window=stringa.length();
        int length=stringb.length();
        for(int start=0; start<=length-window;start++){
            if(stringb.substring(start,start+window).equals(stringa)){
                start=start+window;
                String lastPart;
                if(start<length) lastPart=stringb.substring(start,length);
                else lastPart="";
                return lastPart;
            }
        }
        return stringb;}

        public void printAllgenes(){
            String test="AGTASDKLLTTAAGTSDFTTA";
            //to print all the occurences of genes in dna
            while(test.contains("AGT")){
                String p=findGene(test);
                System.out.println(p);
                test=lastPart(p,test);
            }

        }

   public void testStopCodon(){
        int indexTTA=findStopCodon("asdfasdfAGTSSSTTA",8,"TTA");
        int indexTAG=findStopCodon("asdfasdfAGTSSSSSSTAG",8,"TAG");
        int indexTGA=findStopCodon("asdfasdfAGTSSSTGA",8,"TGA");
        System.out.println(indexTAG+" "+indexTTA+" "+indexTGA);
   }

    public static void main(String args[]){
        Part1 pt= new Part1();
        pt.printAllgenes();
      }


    }

