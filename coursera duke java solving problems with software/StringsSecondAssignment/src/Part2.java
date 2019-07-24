public class Part2 {
    public int howMany(String stringa,String stringb){
        int count=0;
        int window=stringa.length();
        for(int start=0;start<stringb.length()-window ;start++){
            if(stringb.substring(start,start+window).equals(stringa))
                count++;
        }
        return count;
    }

    public void test(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
    }

    public static void main(String args[]){
        Part2 pt= new Part2();
        pt.test();
    }
}
