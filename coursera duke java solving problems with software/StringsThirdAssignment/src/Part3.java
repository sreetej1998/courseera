import edu.duke.*;
public class Part3 {
    public void processGenes() {
        StorageResource st = new StorageResource();
        StorageResource st2 = new StorageResource();
        FileResource fr = new FileResource();
        Part2 cg = new Part2();
        for (String dna : fr.lines()) {
            if (dna.length() > 9)
                st.add(dna);
            if (cg.countCG(dna) > 0.35f)
                st2.add(dna);
        }
        for (String l : st.data())
            System.out.println(l);
        System.out.println(st.size());
        for(String l:st2.data())
            System.out.println(l);
        System.out.println(st2.size());
    }






    public static void main(String args[]){
        Part3 pt= new Part3();
        pt.processGenes();
    }
}
