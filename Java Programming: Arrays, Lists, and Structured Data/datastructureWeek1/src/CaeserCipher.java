import edu.duke.*;
public class CaeserCipher {

    public String encrypt(String input,int key){
        StringBuilder encrypted= new StringBuilder(input);
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower="abcdefghijklmnopqrstuvwxyz";
        String shifted=alphabet.substring(key)+alphabet.substring(0,key);
        String shiftlower=alphabetLower.substring(key)+alphabetLower.substring(0,key);
        for(int index=0;index<encrypted.length();index++){
            boolean isupper=false;
            int ind=0;
            char currChar=encrypted.charAt(index);
            if(Character.isUpperCase(currChar)) isupper=true;
            if(isupper){
                ind=alphabet.indexOf(currChar);
                if(ind!=-1)  encrypted.setCharAt(index,shifted.charAt(ind));
            }
            else{
                ind=alphabetLower.indexOf(currChar);
                if(ind!=-1) encrypted.setCharAt(index,shiftlower.charAt(ind));
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder encrypted= new StringBuilder();
       for(int i=0;i<input.length(); i++){
           if(i%2==0){
               String shiftedChar=encrypt(Character.toString(input.charAt(i)) , key1);
               encrypted.append(shiftedChar);
           }
           else{
               String shiftedChar;
               shiftedChar = encrypt(Character.toString(input.charAt(i)) , key2);
               encrypted.append(shiftedChar);
           }
       }
       return encrypted.toString();

    }

    public void testCeaser(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key=23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        System.out.println(encryptTwoKeys("eeeeeeeeeee",23,17));
    }

    public static void main(String args[]){
        CaeserCipher cc= new CaeserCipher();
        cc.testCeaser();
    }
}
