import org.apache.commons.lang3.StringUtils;
public class Part2 {
public float countCG(String dna){
    int countC= (int) dna.chars().filter(ch->ch=='C').count();
    int countG= (int) dna.chars().filter(ch->ch=='G').count();
    return (float)countC/countG;

}

public int countCTG(String dna){
    int count = StringUtils.countMatches(dna, "CTG");
    return count;
}

public static void main(String args[]){
    Part2 pt= new Part2();
    System.out.println(pt.countCG("CCCCGGGG"));
    System.out.println(pt.countCTG("CTGASDFASDFCTGCTGCTG"));
}
}
