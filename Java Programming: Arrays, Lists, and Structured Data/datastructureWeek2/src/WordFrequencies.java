import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> freqs;

    public WordFrequencies(){
       myWords= new ArrayList<>();
       freqs= new ArrayList<>();
    }

    public void findUnique(){
        FileResource resource= new FileResource();
        myWords.clear();
        freqs.clear();
        StringBuilder sb= new StringBuilder();
        for(String word:resource.words()){
            word= word.toLowerCase();
            int index=myWords.indexOf(word);
            if(index==-1){
                myWords.add(word);
                freqs.add(1);
            }
            else{
                int freq=freqs.get(index);
                freqs.set(index,freq+1);
            }
        }
    }

    public void tester(){
        findUnique();
        for(int i=0;i<myWords.size();i++){
            System.out.println(myWords.get(i)+"\t"+freqs.get(i));
        }
    }

    public static void  main(String args[]){
        WordFrequencies wf= new WordFrequencies();
        wf.tester();
    }
}
