import edu.duke.FileResource;


public class WordsLength {
    public void countWords(FileResource resource,int[] count){
        for(String word:resource.words()){
            int i=0,j=0;
            StringBuilder sb= new StringBuilder("");
            for(i=0,j=word.length()-1;i<j ;){
                if(Character.isLetter(word.charAt(i)) && Character.isLetter(word.charAt(j))) break;
                if(! Character.isLetter(word.charAt(i)) ) i++;
                if(! Character.isLetter(word.charAt(j)) ) j--;
            }
            String correctWord=word.substring(i,j+1);
            count[correctWord.length()]++;
        }
        for(int i=0;i<count.length;i++)
            System.out.println(count[i]+" words of count "+i);
    }

    public static void main(String args[]){
        FileResource fr= new FileResource();
        WordsLength wl= new WordsLength();
        wl.countWords(fr,new int[31]);
    }
}
