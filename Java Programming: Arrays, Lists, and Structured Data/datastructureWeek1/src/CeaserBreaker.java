
public class CeaserBreaker {
    public String decrypt(String input,int key){
        CaeserCipher cc= new CaeserCipher();
        return cc.encrypt(input,26-key);
    }

    public String halfOfStirng(String input,int start){
        StringBuilder sb= new StringBuilder("");
        for(int i=start;i<input.length();i+=2){
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public int[] countLetters(String s){
        int count[]= new int[26];
        String alp="abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<s.length();i++){
            int ind=alp.indexOf(Character.toLowerCase(s.charAt(i)));
            if(ind!=-1)  count[ind]++;
        }
        return count;
    }

    public int maxIndex(int count[]){
        int max=0;
        for(int i=0;i<count.length;i++){
            if(count[i]>max) max=i;
        }
        return max;
    }

    public int getKey(String s){
        int count[]=countLetters(s);
        int maxInd=maxIndex(count);
        int dkey=maxInd-4;
        if(maxInd<4)
            dkey=26-(4-maxInd);
        return dkey;
    }

    public String decryptTwoKeys(String encrypted){
        String a=halfOfStirng(encrypted,0);
        String b=halfOfStirng(encrypted,1);
        int key1=getKey(a);
        int key2=getKey(b);
        System.out.println(key1+" "+key2);
        String deca=decrypt(a,key1);
        String decb=decrypt(b,key2);
        StringBuilder sb= new StringBuilder("");
        for(int i=0;i<deca.length()||i<decb.length();i++){
            if(i<deca.length()) sb.append(deca.charAt(i));
            if(i<decb.length()) sb.append(decb.charAt(i));
        }
        return sb.toString();




    }

    public void testDecrypt(){
        System.out.println(decrypt("cixkhxpa",23));
        System.out.println(halfOfStirng("Qbkm Zgis",0));
        System.out.println(decryptTwoKeys("bvbvbvbvbvb"));
    }

    public static void main(String args[]){
        CeaserBreaker cb= new CeaserBreaker();
        cb.testDecrypt();
    }
}
