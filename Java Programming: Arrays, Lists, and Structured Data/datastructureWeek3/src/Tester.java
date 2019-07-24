
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.desktop.SystemSleepEvent;
import java.util.*;

public class Tester
{
    public void testLogEntry() {
    LogEntry le= WebLogParser.parseEntry("84.189.158.117 - - [21/Sep/2015:07:59:14 -0400] \"GET /favicon.ico HTTP/1.0\" 404 621");
    System.out.println(le);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        la.printAll();
        // complete method
    }

    public void testUniqueIps(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        System.out.println(la.countUniqueIps());
    }

    public void testIpcountrange(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        System.out.println(la.countIPsInRange(200,299));
    }

    public void testUniqueIpVisitsonDay(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        System.out.println(la.uniqueIpVisitsOnDay("Sep 30"));
    }
    public void testprintHigherThannum(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        la.printAllHigherThanNum(200);
    }

    public void testCountVisitPerIP(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("logtest");
        System.out.println(la.countVisitsPerIP());
        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIP()));

    }

    public void testwithHashMap()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("logtest");
        HashMap<String,Integer> counts=la.countVisitsPerIP();
        System.out.println("Most no of vists by ip :"+la.mostNumberVisitsByIP(counts));
        ArrayList<String> mostvistedip=la.iPsMostVisits(counts);
        System.out.println("Ips that are most visited");
        for(String i:mostvistedip)
            System.out.print(i+"\t");
        System.out.println();
        HashMap<String,ArrayList<String>> ipsonday=la.iPsForDays();
        System.out.println("Day on which most ips visited: "+la.dayWithMostIPVisits(ipsonday));
        ArrayList<String> ipswithmostvistsonaday=la.iPsWithMostVisitsOnDay(ipsonday,"Sep 30");
        System.out.println("ipswithmostvistsonaday on sep 30");
        for(String i:ipswithmostvistsonaday)
            System.out.print(i+"\t");
    }

    public static void main(String args[]){
        Tester t= new Tester();
        t.testLogEntry();
        t.testLogAnalyzer();
        t.testUniqueIps();
        t.testIpcountrange();
        t.testUniqueIpVisitsonDay();
        t.testprintHigherThannum();
        t.testCountVisitPerIP();
        t.testwithHashMap();
    }
}