package Day6;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 extends Day {

    public Day6(){
        super(6);
        this.registerTestBothParts("abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b");
    }


    @Override
    public void part1(List<String> inputs, List<Object> params) {
        inputs.add("");
        List<Character> yesAnswers = new ArrayList<>(26);
        int sumYes = 0;
        int groupYes = 0;
        for(String s : inputs){
            if(s.length() == 0){
                Logger.debug("Group: " +groupYes);
                sumYes += groupYes;
                groupYes = 0;
                yesAnswers.clear();
            }
            for(char c : s.toCharArray()){
                if(!yesAnswers.contains(c)) {
                    yesAnswers.add(c);
                    groupYes++;
                }
            }

        }
        Logger.result("Sum: " + sumYes);

    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        inputs.add("");
        int sumYes = 0;
        int groupCount = 0;
        Map<Character,Integer> charCount = new HashMap<>();
        for(String s : inputs){
            if(s.length() == 0){
                int groupYes = 0;
                for(char c : charCount.keySet()){
                    if(charCount.get(c) == groupCount){
                        groupYes++;
                    }
                }
                Logger.debug("Group: " +groupYes);
                sumYes += groupYes;
                groupCount = 0;
                charCount.clear();
                continue;
            }
            groupCount++;
            for(char c : s.toCharArray()){
                charCount.merge(c, 1, Integer::sum);
            }

        }
        Logger.result("Sum: "+sumYes);
    }
}
