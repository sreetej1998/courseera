import edu.duke.URLResource;
public class Part4 {
    public void printLinks(){
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String line:url.lines()){
                parseLine(line);
        }
    }

    public void parseLine(String line){
        String a="https://www.youtube.com";
        String b="http://www.youtube.com";
        String temp="";
        int start=0,end=0;
        if(line.contains(a))  start=line.indexOf(a);
        else if(line.contains(b))  start=line.indexOf(b);
        else start=-1;

        if(start>=0){
            temp=line.substring(start,line.length());
            end=temp.indexOf("\"");
            System.out.println(line.substring(start,end));
        }
    }


    public static void main(String g[]){
        new Part4().printLinks();
    }
}
