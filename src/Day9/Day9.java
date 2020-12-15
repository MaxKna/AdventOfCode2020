package Day9;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends Day {

    public Day9() {
        super(9);
        this.registerTestBothParts("35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576", 5);
        this.registerTaskParams(25);
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        List<Long> longs = inputs.stream().map(Long::parseLong).collect(Collectors.toList());
        Logger.result(checkXMASCode(new ArrayList<>(longs),(Integer) params.get(0)));
    }

    private long checkXMASCode(List<Long> numbers, int preambleLength) {
        List<Long> candidates = new LinkedList<>();
        for (int i = 0; i < preambleLength; i++) {
            candidates.add(numbers.get(i));
        }
        numbers = numbers.subList(preambleLength,numbers.size());
        for(long l : numbers){
            boolean valid = isValid(candidates,l);
            candidates.remove(0);
            candidates.add(l);
            if(!valid){
                Logger.debug("Crashing number: "+l);
                return l;
            }
        }

        return -1;

    }

    private boolean isValid(List<Long> candidates, long sum) {
        for (int j = 0; j < candidates.size(); j++) {
            for (int k = j + 1; k < candidates.size(); k++) {
                if (candidates.get(j) + candidates.get(k) == sum) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        List<Long> longs = inputs.stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
        long goal = checkXMASCode(new ArrayList<>(longs),(Integer) params.get(0));
        for(int i = 0; i< longs.size(); i++){
            long sum = longs.get(i);
            long min = sum;
            long max = sum;
            for(int j = i+1; j< longs.size(); j++){
                long add = longs.get(j);
                sum += add;
                if(add > max){
                    max = add;
                }else if(add < min){
                    min = add;
                }
                if(sum == goal){
                    Logger.debug("FOUND! i: "+i+" j: "+j);
                    Logger.debug("Max: "+max);
                    Logger.debug("Min: "+min);
                    Logger.debug("Sum: "+(min+max));
                }else if(sum >= goal){
                    break;
                }
            }
        }
    }
}
