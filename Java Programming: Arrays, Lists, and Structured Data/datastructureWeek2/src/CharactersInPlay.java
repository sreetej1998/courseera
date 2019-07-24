import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> persons;
    private ArrayList<Integer> counter;

    public CharactersInPlay(){
        persons= new ArrayList<>();
        counter= new ArrayList<>();
    }
    public void update(String person){
        int index=persons.indexOf(person);
        if(index==-1){
            persons.add(person);
            counter.add(1);
        }
        else{
            int count=counter.get(index);
            counter.set(index,count+1);
        }
    }

    public void findAllCharacters(){
        FileResource fr= new FileResource();
        for(String line:fr.lines()){
            int index=0;
            if(line.contains("."))
                 index=line.indexOf(".");
                update(line.substring(0,index).trim());

        }
    }
    public void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<persons.size();i++){
            if(counter.get(i)>num1 && counter.get(i)<num2)
                System.out.println(persons.get(i));
        }

    }

    public void tester(){
        findAllCharacters();
//        for(int i=0;i<persons.size();i++){
//            System.out.println(persons.get(i)+" : "+counter.get(i));
//        }
        charactersWithNumParts(1,4);
    }

    public static  void main(String args[]){
        CharactersInPlay c= new CharactersInPlay();
        c.tester();

    }
}
