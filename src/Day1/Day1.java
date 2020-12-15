package Day1;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.List;

public class Day1 extends Day{

    public Day1() {
        super(1);
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {

        List<Integer> ints = new ArrayList<>();

        for(String in: inputs){
            int intIn = Integer.parseInt(in);
            for(Integer i : ints){
                if(intIn + i == 2020){
                    Logger.debug(intIn+" + "+i+" = "+(intIn+i));
                    Logger.result(intIn * i);
                    return;
                }
            }
            ints.add(intIn);
        }
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {

        List<Integer> ints = new ArrayList<>();

        for(String in: inputs){
            int intIn = Integer.parseInt(in);
            for(int i = 0; i< ints.size(); i++){
                for(int j = i+1; j < ints.size(); j++)
                if(intIn + ints.get(i) + ints.get(j) == 2020){
                    Logger.debug(intIn+" + "+ints.get(i)+" + "+ints.get(j)+" = "+(intIn+ints.get(i)+ints.get(j)));
                    Logger.result(intIn * ints.get(i)*ints.get(j));
                    return;
                }
            }
            ints.add(intIn);
        }
    }
}