package Day3;

import util.Day;
import util.Logger;

import java.util.List;

public class Day3 extends Day {

    public Day3() {
        super(3);
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        Logger.result(checkTrees(inputs,3,1));
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        long mult = checkTrees(inputs, 1,1);
        mult *= checkTrees(inputs,3,1);
        mult *= checkTrees(inputs,5,1);
        mult*= checkTrees(inputs,7,1);
        mult *= checkTrees(inputs,1,2);
        Logger.result(mult);
    }

    private static int checkTrees(List<String> inputs, int xStep, int yStep){
        int width = inputs.get(0).length();

        int x = 0;
        int y = 0;

        int countTree = 0;
        try {
            while (true) {
                x = (x + xStep) % width;
                y = y + yStep;

                char c = inputs.get(y).charAt(x);
                if (c == '#') {
                    countTree++;
                }

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        Logger.debug("For x+"+xStep+" and y+"+yStep+": "+countTree);
        return countTree;
    }
}
