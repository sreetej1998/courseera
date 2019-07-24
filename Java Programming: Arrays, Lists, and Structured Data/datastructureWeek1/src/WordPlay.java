public class WordPlay {
    public boolean isVowel(char ch){
        return ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U';
    }


    public String replaceVowels(String s){
        StringBuilder sb= new StringBuilder();
        for(char i:s.toCharArray()){
            if(isVowel(i)) sb.append('*');
            else sb.append(i);
        }
        return sb.toString();
    }


    public String emphasize(String s,char ch){
        StringBuilder sb= new StringBuilder();
        for(int index=0;index<s.length();index++){
            if(s.charAt(index)==ch &&index%2==0) sb.append('*');
            else if(s.charAt(index)==ch && index%2!=0) sb.append('+');
            else sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    public void tester(){
        System.out.println(replaceVowels("HelloWorld"));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));


    }
    public static void main(String args[]){
        WordPlay wp= new WordPlay();
        wp.tester();

    }



}
