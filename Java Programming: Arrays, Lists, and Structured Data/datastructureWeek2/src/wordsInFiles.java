import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class wordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public wordsInFiles(){
        map= new HashMap<>();
    }
    private void addWordsFromFile(File f){
        FileResource fr= new FileResource(f);

        for(String word:fr.words()){
            if(map.containsKey(word)){
                ArrayList<String> list= map.get(word);
                list.add(f.getName());
            }
            else{
                ArrayList<String> l= new ArrayList<>();
                l.add(f.getName());
                map.put(word,l);
            }
        }
    }

    public int getMaximum(){
        int max=0;

        for(Map.Entry<String,ArrayList<String>> entry:map.entrySet()){
            if(entry.getValue().size()>max){
                max=entry.getValue().size();

            }
        }
        return max;
    }

    public ArrayList<String> wordInNumFiles(int num){
        ArrayList<String> words= new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> entry:map.entrySet()){
            if(entry.getValue().size()==num){
                words.add(entry.getKey());
            }
        }
        return words;
    }

    public void printFilein(String word){
        for(Map.Entry<String,ArrayList<String>> entry:map.entrySet()){
         if(entry.getKey().equals(word)){
             for(String s:entry.getValue())
                 System.out.println(s);
         }
        }
    }
    public void tester(){
      buildWordFileMap();
        for(Map.Entry<String,ArrayList<String>> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println(getMaximum());

        for(String s:wordInNumFiles(2)) {
            System.out.println(s);
        }
        printFilein("reddy");
    }

    public void buildWordFileMap(){
        map.clear();
        DirectoryResource res= new DirectoryResource();
        for(File f:res.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public static void main(String args[]){
        wordsInFiles w= new wordsInFiles();
        w.tester();
    }
}
