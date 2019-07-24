public class Part1 {
    public String findSimpleGene(String dna){
        StringBuilder gene= new StringBuilder("");
        int start=0;
        while(start<dna.length()-3){
            if(dna.substring(start,start+3).equals("AGT")){
                while(start<dna.length()-3 && !dna.substring(start,start+3).equals("TAA")){
                    gene.append(dna.charAt(start));
                    start+=1;
                }
                if(start<dna.length()){
                    gene.append("TAA");
                    String g=gene.toString();
                    if(g.length()%3==0) return g;
                }
            }
            else start+=1;
        }
        return "";
    }

    public void testSimpleGene(){
        //string with AGT and TTA
        System.out.println(findSimpleGene("ASDFASDFAGTSRETAA"));
        //String without AGT
        System.out.println(findSimpleGene("TAAASDFASDFASDFASDFASDFSFTAA"));
        //String without TTA
        System.out.println(findSimpleGene("AGTASDFASDFASDFASDFASDF"));
        //String without both AGT and TTA
        System.out.println(findSimpleGene("ADSFADFDFSASDFASDFASDFEWQWERQWERQWER"));

    }

    public static void main(String args[]){
        Part1 pt= new Part1();
        pt.testSimpleGene();
    }

}
