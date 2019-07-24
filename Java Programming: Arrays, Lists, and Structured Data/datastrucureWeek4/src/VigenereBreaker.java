
import java.nio.charset.CodingErrorAction;
import java.sql.Array;
import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb= new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            sb.append(message.charAt(i));
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc= new CaesarCracker();
        for(int i=0;i<klength;i++)
            key[i]=cc.getKey(sliceString(encrypted,i,klength));
        return key;
    }
    public HashSet<String> readDictionary(FileResource res)
    {
        HashSet<String> hs= new HashSet<>();
        for(String words:res.lines())
            hs.add(words.toLowerCase());
        return  hs;
    }
    public int countWords(String msg,HashSet<String> hs)
    {
        int count=0;
        String words[]=msg.split("\\W");
        for(String word:words)
        {
            if(hs.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dict)
    {
        int maxwords=0,temp=0;
        int key[]=null;
        for(int i=1;i<=100;i++)
        {
            int k[]=tryKeyLength(encrypted,i,'e');
            String decrypted=new VigenereCipher(k).decrypt(encrypted);
            temp=countWords(decrypted,dict);
            if(maxwords<temp)
            {
                maxwords=temp;
                key=k;
            }
        }
        System.out.println(maxwords);
        return new VigenereCipher(key).decrypt(encrypted);
    }
    public char mostCommonCharIn(HashSet<String> dict)
    {
        HashMap<Character,Integer> charCounts = new HashMap<>();
        for(String s : dict)
        {
            for(char c : s.toLowerCase().toCharArray())
            {
                if(!charCounts.containsKey(c))
                    charCounts.put(c,1);
                else
                    charCounts.put(c,charCounts.get(c)+1);
            }
        }
        int max = 0;
        char mostCommonChar = '0';
        for(char c : charCounts.keySet())
        {
            if(charCounts.get(c) > max)
            {
                max = charCounts.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    public void brealForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages)
    {
        int maxwords=0,tmp=0;
        String keylang=null,decrypted=null,ans=null;
        for(String lang:languages.keySet())
        {
            decrypted=breakForLanguage(encrypted,languages.get(lang));
            tmp=countWords(decrypted,languages.get(lang));
            if(maxwords<tmp)
            {
                maxwords=tmp;
                keylang=lang;
                ans=decrypted;
            }
        }
        System.out.println("Language :"+keylang);
        System.out.println("Decrypted :"+ans);
    }
    //---------for known key length
//    public void breakVigenere () {
//        String msg=new FileResource().asString();
//        int []keys=tryKeyLength(msg,5,'e');
//        VigenereCipher vc=new VigenereCipher(keys);
//        System.out.println(vc.decrypt(msg));
//    }
//---------for unknown key length
//    public void breakVigenere () {
//        String msg=new FileResource().asString();
//        HashSet<String> dict=readDictionary(new FileResource());
//        String decryptrd=breakForLanguage(msg,dict);
//        System.out.println(decryptrd);
//    }
    public void breakVigenere()
    {
        String msg=new FileResource().asString();
        DirectoryResource dir = new DirectoryResource();
        HashMap<String,HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for(File f : dir.selectedFiles())
        {
            System.out.println("Reading words in "+f.getName());
            languages.put(f.getName(),readDictionary(new FileResource(f)));
        }
        brealForAllLangs(msg,languages);
    }
    public static void main(String args[])
    {
        VigenereBreaker o= new VigenereBreaker();
        o.breakVigenere();
    }

}