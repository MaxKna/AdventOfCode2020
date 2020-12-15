package Day15;

import util.Day;
import util.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 extends Day {

    public Day15(){
        super(15);
        this.registerTestPartOne("0,3,6", 10);
        this.registerTestPartOne("0,3,6", 2020);
        this.registerTestPartOne("1,3,2", 2020);
        this.registerTestPartOne("2,1,3", 2020);
        this.registerTestPartOne("1,2,3", 2020);
        this.registerTestPartOne("2,3,1", 2020);
        this.registerTestPartOne("3,2,1", 2020);
        this.registerTestPartOne("3,1,2", 2020);
        this.registerTask1Params(2020);
        this.registerTask2Params(30000000);
    }
    @Override
    public void part1(List<String> inputs, List<Object> params) {
        List<Integer> ints = Arrays.stream(inputs.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Logger.result(this.playMemory(ints, (Integer) params.get(0)));
    }

    private int playMemory(List<Integer> ints, int turns){
        Map<Integer,List<Integer>> usedNumbers = new HashMap<>();
        int lastNumber = -1;
        for(int i = 0; i< ints.size() && i < turns; i++){
            lastNumber = ints.get(i);
            usedNumbers.put(lastNumber,new ArrayList<>());
            usedNumbers.get(lastNumber).add(i);
        }
        for(int i = ints.size(); i< turns; i++){
            if(usedNumbers.get(lastNumber).size() == 1){
                lastNumber = 0;
            }else{
                List<Integer> indices = usedNumbers.get(lastNumber);
                lastNumber = indices.get(indices.size()-1)-indices.get(indices.size()-2);
            }

            if (!usedNumbers.containsKey(lastNumber)) {
                usedNumbers.put(lastNumber, new ArrayList<>());
            }
            usedNumbers.get(lastNumber).add(i);
        }
        Logger.debug(usedNumbers);
        return lastNumber;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        List<Integer> ints = Arrays.stream(inputs.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Logger.result(this.playMemory(ints,(Integer) params.get(0)));
    }
}
