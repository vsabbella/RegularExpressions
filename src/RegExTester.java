import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTester {

    public static void main(String[] args){
        System.out.println("test");
        //playwithRegexMatches();
        //patternAndMatcherTest();
        //patternAndMatcherTest();
        //playWithQuantifiers();
        //testCaptureGroups();
        //testNamedCaptureGroups();
        //control flags (?i) - turn off case insensitvity
        //testControlFlagsInCaptureGroups();
        //testBackReferences();
        //testReplaceAllWithCaptureGroups();
        //Anchors and Boundaries
        //testAnchorsAndBoundaries1();
        //testAnchorsAndBoundaries2();
        testAnchorsAndBoundaries3();

    }

    private static void testAnchorsAndBoundaries3() {
       String inputString = "Jack and Jill \\n went up the hill \\n to fetch their fill of water \\n Jack got ill and tore the bill \\n and Jill ignored the drill";
        Pattern pattern = Pattern.compile("(?m)^Jack|ill$");
        matchandDisplay(inputString,pattern);
        System.out.println("----------------------------");

    }


    private static void testAnchorsAndBoundaries1() {
        String inputString = "Her man Herman";
        Pattern pattern = Pattern.compile("\\b(Her)\\b");
        matchandDisplay(inputString,pattern);
        System.out.println("----------------------------");

    }
    private static void testAnchorsAndBoundaries2() {
        String inputString = "Her man Herman";
        Pattern pattern = Pattern.compile("\\b(Her|man)\\b");
        matchandDisplay(inputString,pattern);
        /*Output:
        *   Group Count:1
            0.Her
            1.Her
            0.man
            1.man
        *
        * */
    }
    private static void matchandDisplay(String inputString, Pattern pattern){
        Matcher m = pattern.matcher(inputString);
        int count = m.groupCount();
        System.out.printf("%s:%s%n","Group Count", count);
        if(m.matches()){
            System.out.printf("%s%n",m.group(0));
        }
       while(m.find()) {

            for (int i = 0; i <= count; i++){
                System.out.printf("%s.%s%n",i, m.group(i));
            }


        }
    }

    private static void testReplaceAllWithCaptureGroups() {
        String str1 ="securities-development-equities-valuation-asia";
        String str2 = "fixed_income-development-equities-emea";
        String str3 = "fx-development-america";
        String str4 = str1.replaceAll("(?<BusinessUnit>\\w+)(-(\\w+))+-(?<Region>\\w+)","BusinessUnit: $1, Region:${Region} ");
        System.out.println(str4);
    }

    private static void testBackReferences() {

    }

    static void playwithRegexMatches(){
        System.out.println(Arrays.asList("Anne of the 1000 days.".split(" ")));
        //[Anne, of, the, 1000, days.]
        System.out.println(Arrays.asList("Anne of the 1000 days.".split(".")));
        //[] - split removes the characters that are matched and . matches every character.

    }
     static void patternAndMatcherTest(){
        Pattern pattern = Pattern.compile("/");
         Matcher matcher = pattern.matcher("12/21/18");
         String replaced = matcher.replaceAll("-");
         System.out.println(replaced);

    }
    static void playWithQuantifiers(){
        //split the words by trimming spaces.
        String str="this,  is  ,  a ,good  ,    quote";
        System.out.println(Arrays.asList(str.split("\\s*,\\s*")));
    }
    static void testCaptureGroups(){
        String str1 ="securities-development-equities-valuation-asia";
        String str2 = "fixed_income-development-equities-emea";
        String str3 = "fx-development-america";
        Pattern pattern = Pattern.compile("(\\w+)(-(\\w+))+-(\\w+)");
        display(pattern,str1);
        display(pattern,str2);
        display(pattern,str3);
    }
    static void display(Pattern pattern, String group){

        Matcher matcher = pattern.matcher(group);
        int count =matcher.groupCount();
            if(matcher.matches()){
                for(int i=-0;i<=count;i++) {
                    System.out.printf("%s.%s%n", i, matcher.group(i));
                }
            }
    }
    static void testNamedCaptureGroups(){
        String str1 ="securities-development-equities-valuation-asia";
        String str2 = "fixed_income-development-equities-emea";
        String str3 = "fx-development-america";
        Pattern pattern = Pattern.compile("(?<BusinessUnit>\\w+)(-(\\w+))+-(?<Region>\\w+)");
        displayGroup(pattern,str1,"BusinessUnit","Region");
        displayGroup(pattern,str2,"BusinessUnit","Region");
        displayGroup(pattern,str3,"BusinessUnit","Region");
    }
    static void displayGroup(Pattern pattern, String label, String... groups){

        Matcher matcher = pattern.matcher(label);
        int count =matcher.groupCount();
        if(matcher.matches()){
            for(String group:groups) {
                System.out.printf("%s.%s%n", group, matcher.group(group));
            }
        }
    }
    static void testControlFlagsInCaptureGroups(){
        //case Insensivity turned on
        Pattern pattern = Pattern.compile("(hot digity)");
        display2(pattern,"Hot Digity Dog hot digity dog");
        System.out.println("\n------------------------------------"  );
        //case Insensivity turned off
        pattern = Pattern.compile("((?i)hot digity)");
        display2(pattern,"Hot Digity Dog hot digity dog");

    }
    static void display2(Pattern pattern, String group){

        Matcher matcher = pattern.matcher(group);
        int count =matcher.groupCount();
        while(matcher.find()){
            System.out.printf("%s%n",  matcher.group(0));
        }
    }

}
