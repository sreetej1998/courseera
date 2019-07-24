public class Part3 {
    public boolean twoOccurences(String stringa,String stringb){
        int count=0;
        int window=stringa.length();
        for(int start=0;start<stringb.length()-window && count<=2;start++){
        if(stringb.substring(start,start+window).equals(stringa))
            count++;
        }
        if(count>=2) return true;
        else return false;
    }

    public String lastPart(String stringa,String stringb){
        int window=stringa.length();
        int length=stringb.length();
        for(int start=0; start<length-window;start++){
            if(stringb.substring(start,start+window).equals(stringa)){
                start=start+window;
                String lastPart;
                if(start<length) lastPart=stringb.substring(start,length);
                else lastPart="";
                return lastPart;
            }
        }
        return stringb;
    }

    public void tester(){
        //true case
        System.out.println(twoOccurences("by","A story by Abby Long"));
        //false case
        System.out.println(twoOccurences("by","A story by Abb Long"));

        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }

    public static void main(String args[]){
        Part3 pt= new Part3();
        pt.tester();
    }
}
