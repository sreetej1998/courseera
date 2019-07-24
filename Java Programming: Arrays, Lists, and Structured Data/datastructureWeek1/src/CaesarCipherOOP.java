import edu.duke.*;
public class CaesarCipherOOP {

    private String alphabet;
    private String alphabetLower;
    private int key,key1,key2;
    private String shifted;
    private String shiftlower;

    public CaesarCipherOOP(int key){
        this.key=key;
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower="abcdefghijklmnopqrstuvwxyz";
        shifted=alphabet.substring(key)+alphabet.substring(0,key);
        shiftlower=alphabetLower.substring(key)+alphabetLower.substring(0,key);
    }


    public String encrypt(String input){
        StringBuilder encrypted= new StringBuilder(input);
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
        CaesarCipherOOP cc= new CaesarCipherOOP(key1);
        CaesarCipherOOP cc1= new CaesarCipherOOP(key2);
        StringBuilder encrypted= new StringBuilder();
        for(int i=0;i<input.length(); i++){
            if(i%2==0){
                String shiftedChar=cc.encrypt(Character.toString(input.charAt(i)));
                encrypted.append(shiftedChar);
            }
            else{
                String shiftedChar;
                shiftedChar = cc1.encrypt(Character.toString(input.charAt(i)));
                encrypted.append(shiftedChar);
            }
        }
        return encrypted.toString();

    }

    public void testCeaser(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);
        System.out.println(encryptTwoKeys("eeeeeeeeeee",23,17));
    }

    public static void main(String args[]){
        CaesarCipherOOP cc= new CaesarCipherOOP(23);
        cc.testCeaser();
    }
}
