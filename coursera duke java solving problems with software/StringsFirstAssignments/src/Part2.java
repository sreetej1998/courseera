public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String endCodon){
        StringBuilder gene= new StringBuilder("");
        int start=0;
        while(start<dna.length()-3){
            if(dna.substring(start,start+3).equals(startCodon)){
                while(start<dna.length()-3 && !dna.substring(start,start+3).equals(endCodon)){
                    gene.append(dna.charAt(start));
                    start+=1;
                }
                if(start<dna.length()){
                    gene.append(endCodon);
                    String g=gene.toString();
                    if(g.length()%3==0) return g;
                }
            }
            else start+=1;
        }
        return "";
    }
    public void testSimpleGene(){
        //tests
        System.out.println(findSimpleGene("ASDFASDFAGTSRETAA","AGT","TAA"));
        System.out.println(findSimpleGene("AWWSRETEJQWE","AWW","TEJ"));
        System.out.println(findSimpleGene("AGTASDFASDFASDFASDFASDF","WW","QW"));
        System.out.println(findSimpleGene("ADSFADFDFSASDFASDFASDFEWQWERQWERQWER","QW","R"));

    }

    public static void main(String args[]){
        Part2 pt= new Part2();
        pt.testSimpleGene();
    }

}
