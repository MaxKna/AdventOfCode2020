package Day2;

import util.Day;
import util.Logger;

import java.util.List;

public class Day2 extends Day {


    public Day2() {
        super(2);
    }

    @Override
    public void part1(List<String> inputs, List<Object> params){
        int inValidCount = 0;
        int validCount = 0;

        for(String input : inputs){
            String[] split = input.split(" ");

            String[] margin = split[0].split("-");
            int min = Integer.parseInt(margin[0]);
            int max = Integer.parseInt(margin[1]);

            char policy = split[1].charAt(0);

            int internalCount = 0;

            for(char c : split[2].toCharArray()){
                if(c == policy){
                    internalCount++;
                }
            }

            if(internalCount < min || internalCount > max){
                inValidCount++;
            }else{
                validCount++;
            }
        }

        Logger.result("Invalid: " + inValidCount + " Valid: " + validCount);
    }

    @Override
    public void part2(List<String> inputs, List<Object> params){

        int inValidCount = 0;
        int validCount = 0;

        for(String input : inputs){
            String[] split = input.split(" ");

            String[] margin = split[0].split("-");
            int index1 = Integer.parseInt(margin[0])-1;
            int index2 = Integer.parseInt(margin[1])-1;

            char policy = split[1].charAt(0);


            if(split[2].charAt(index1) == policy && split[2].charAt(index2) == policy){
                inValidCount++;
            }else if (split[2].charAt(index1) == policy || split[2].charAt(index2) == policy){
                validCount++;
            }else{
                inValidCount++;
            }
        }

        Logger.result("Invalid: "+inValidCount+" Valid: "+validCount);
    }
}
