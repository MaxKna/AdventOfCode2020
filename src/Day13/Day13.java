package Day13;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends Day {

    public Day13() {
        super(13);
        this.registerTestPartTwo("\n17,x,13,19");
        this.registerTestBothParts("939\n" +
                "7,13,x,x,59,x,31,19\n");
        this.registerTestPartTwo("\n67,7,59,61");
        this.registerTestPartTwo("\n67,x,7,59,61");
        this.registerTestPartTwo("\n67,7,x,59,61");
        this.registerTestPartTwo("\n1789,37,47,1889");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        int timestamp = Integer.parseInt(inputs.get(0));
        String[] busses = inputs.get(1).split(",");
        int minimum = Integer.MAX_VALUE;
        Bus miniBus = null;
        for (String bus : busses) {
            if (bus.equals("x")) {
                continue;
            }
            Bus b = new Bus(Integer.parseInt(bus));
            int diff = b.iterateOver(timestamp) - timestamp;
            if (diff < minimum) {
                minimum = diff;
                miniBus = b;
            }
        }
        assert miniBus != null;
        Logger.debug("Difference : " + minimum);
        Logger.debug(miniBus);
        Logger.result("Multiplication: " + (miniBus.number * minimum));
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        String[] busses = inputs.get(1).split(",");
        List<Bus> busList = new ArrayList<>();
        for (String bus : busses) {
            Bus b = null;
            if (!bus.equals("x")) {
                b = new Bus(Integer.parseInt(bus));
            }
            busList.add(b);
        }
        Logger.result(Bus.getSequence(busList));
    }

    private static class Bus {

        int number;

        public Bus(int number) {
            this.number = number;
        }

        public int iterateOver(int goal) {
            int min = 0;
            while (min < goal) {
                min += this.number;
            }
            return min;
        }


        public String toString() {
            return "Bus " + number;
        }

        public static long getSequence(List<Bus> busList) {
            List<Long> ids = busList.stream().map(b -> b == null ? -1L : (long) b.number).collect(Collectors.toList());
            long lcm = -1, time = -1;
            int index = 0;
            while (true) {
                final long id = ids.get(index);
                if (id == -1) {
                    index++;
                    continue;
                }

                if (lcm == -1) {
                    lcm = id;
                    time = id - index;
                    index++;
                    continue;
                }

                if ((time + index) % id == 0) {
                    if (++index >= ids.size()) {
                        return time;
                    }

                    lcm *= id;
                    continue;
                }

                time += lcm;
            }
        }
    }
}

