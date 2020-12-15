package Day10;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 extends Day {

    public Day10() {
        super(10);
        this.registerTestBothParts("16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4");
        this.registerTestBothParts("28\n" +
                "33\n" +
                "18\n" +
                "42\n" +
                "31\n" +
                "14\n" +
                "46\n" +
                "20\n" +
                "48\n" +
                "47\n" +
                "24\n" +
                "23\n" +
                "49\n" +
                "45\n" +
                "19\n" +
                "38\n" +
                "39\n" +
                "11\n" +
                "1\n" +
                "32\n" +
                "25\n" +
                "35\n" +
                "8\n" +
                "17\n" +
                "7\n" +
                "9\n" +
                "4\n" +
                "2\n" +
                "34\n" +
                "10\n" +
                "3");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        List<Integer> ints = inputs.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        Logger.result(getMaximumJoltage(ints));
    }

    private int getMaximumJoltage(List<Integer> inputs) {
        int level = 0;
        int[] differences = new int[3];
        for (int input : inputs) {
            int diff = input - level;
            if (diff > 3) {
                //Logger.debug("Error");
                return -1;
            } else {
                differences[diff - 1] += 1;
                level = input;
            }

        }
        differences[2]++;
        Logger.debug("Differences: "+ Arrays.toString(differences));
        Logger.debug("Multiplications: "+differences[0]*differences[2]);
        return level + 3;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {

        List<Integer> ints = inputs.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        int goal = getMaximumJoltage(ints);
        ints.add(0, 0);
        ints.add(goal);
        Map<Integer, Adapter> adapterMap = ints.stream().map(num -> new Adapter(num)).collect(Collectors.toMap(adapter -> adapter.jolts, adapter -> adapter));
        adapterMap.values().forEach(adapter -> adapter.setPossibleConnections(adapterMap));
        Logger.result(adapterMap.get(0).getNumPossibilities());

    }

    private static class Adapter {
        List<Adapter> possibleConnections = new ArrayList<>();
        Integer jolts;
        Long permutations;

        public Adapter(int jolts) {
            this.jolts = jolts;
        }

        public void setPossibleConnections(Map<Integer, Adapter> adapterMap) {
            IntStream.rangeClosed(1, 3).forEach(index -> {
                if (adapterMap.containsKey(jolts + index)) {
                    possibleConnections.add(adapterMap.get(jolts + index));
                }
            });
        }

        public Long getNumPossibilities() {
            if (permutations != null) {
                return permutations;
            }

            permutations = possibleConnections.stream()
                    .mapToLong(Adapter::getNumPossibilities)
                    .sum();

            if (possibleConnections.isEmpty()) {
                permutations = 1L;
            }

            return permutations;
        }
    }
}

