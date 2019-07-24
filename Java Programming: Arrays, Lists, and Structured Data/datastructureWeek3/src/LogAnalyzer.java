
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records= new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr= new FileResource(filename);
        for(String line:fr.lines())
            records.add(WebLogParser.parseEntry(line));
    }

    public int countUniqueIps()
    {
        HashSet<String> uniqips=new HashSet<>();
        for( LogEntry le:records)
        {
            String ip=le.getIpAddress();
            uniqips.add(ip);
        }
        return uniqips.size();
    }

    public ArrayList<String> uniqueIpVisitsOnDay(String day)
    {
        ArrayList<String > uniqips=new ArrayList<>();
        for(LogEntry le:records)
        {
            Date date=le.getAccessTime();
            String d=date.toString().substring(4,10);
            if(d.equals(day))
            {
                String ip=le.getIpAddress();
                if(!uniqips.contains(ip))
                    uniqips.add(ip);
            }
        }
        return uniqips;
    }

    public void printAllHigherThanNum(int num)
    {
        for(LogEntry le :records)
        {
            if(le.getStatusCode()>num)
                System.out.println(le);
        }
    }

    public int countIPsInRange(int low,int high){
        int validStatuscount=0;
        for(LogEntry le:records){
            int statusCode=le.getStatusCode();
            if(low<=statusCode && statusCode<=high)
                validStatuscount++;
        }
        return validStatuscount;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String ,Integer> map= new HashMap<>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            if(map.containsKey(ip)) map.put(ip,map.get(ip)+1);
            else map.put(ip,1);
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        return Collections.max(counts.values());
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts)
    {
        int max = mostNumberVisitsByIP(counts);
        ArrayList<String> ans = new ArrayList<>();
        for(String ip : counts.keySet())
            if(counts.get(ip) == max)
                ans.add(ip);
        return ans;
    }

    public HashMap<String,ArrayList<String>> iPsForDays()
    {
        HashMap<String,ArrayList<String>> ans = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records) {
            String key = le.getAccessTime().toString().substring(4,10);
            if(ans.containsKey(key)) {
                ArrayList<String> temp = ans.get(key);
                temp.add(le.getIpAddress());
                ans.put(key,temp);
            }
            else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(le.getIpAddress());
                ans.put(key,temp);
            }
        }
        return ans;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysMap) {
        int max = 0;
        String date = "";
        for(String currDay : daysMap.keySet()) {
            if(max < daysMap.get(currDay).size()) {
                max = daysMap.get(currDay).size();
                date = currDay;
            }
        }
        return date;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> daysMap, String date)
    {
        ArrayList<String> ipsOnTheGivenDay = daysMap.get(date);
        HashMap<String,Integer> counts = new HashMap<>();
        for(String s : ipsOnTheGivenDay)
        {
            if(counts.containsKey(s))
                counts.put(s,counts.get(s)+1);
            else
                counts.put(s,1);
        }
        return iPsMostVisits(counts);
    }




}