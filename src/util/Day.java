package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Day {

    private final List<List<String>> testsPartOne = new ArrayList<>();
    private final List<List<String>> testsPartTwo = new ArrayList<>();

    private final List<List<Object>> test1Params = new ArrayList<>();
    private final List<List<Object>> test2Params = new ArrayList<>();
    private List<Object> task1Params;
    private List<Object> task2Params;

    private static final Day[] days = new Day[24];

    public Day(int number){
        days[number-1] = this;
    }

    public abstract void part1(List<String> inputs, List<Object> params);

    public abstract void part2(List<String> inputs, List<Object> params);

    public void registerTestPartOne(String test){
        registerTestPartOne(test, new Object[1]);
    }

    public void registerTestPartOne(String test, Object ... params) {
        testsPartOne.add(InputReader.readTest(test));
        test1Params.add(Arrays.asList(params));
    }

    public void registerTestPartTwo(String test){
        registerTestPartTwo(test, new Object[1]);
    }

    public void registerTestPartTwo(String test, Object ... params) {
        testsPartTwo.add(InputReader.readTest(test));
        test2Params.add(Arrays.asList(params));
    }

    public void registerTestBothParts(String test){
        registerTestBothParts(test, new Object[1]);
    }

    public void registerTestBothParts(String test, Object ... objects) {
        this.registerTestPartOne(test,objects);
        this.registerTestPartTwo(test,objects);
    }

    public void registerTaskParams(Object ... params){
        task1Params = Arrays.asList(params);
        task2Params = Arrays.asList(params);
    }

    public void registerTask1Params(Object ... params){
        task1Params = Arrays.asList(params);
    }

    public void registerTask2Params(Object ... params){
        task2Params = Arrays.asList(params);
    }

    public static void run(TestMode testMode, RunMode runMode) {
        List<Day> days = new ArrayList<>();
        if(runMode == RunMode.LATEST){
            long count = Arrays.stream(Day.days).filter(Objects::nonNull).count();
            days.addAll(Arrays.stream(Day.days).filter(Objects::nonNull).skip(count-1).collect(Collectors.toList()));
        }else if(runMode == RunMode.ALL){
            days.addAll(Arrays.stream(Day.days).filter(Objects::nonNull).collect(Collectors.toList()));
        }
        for(Day d :days) {
            Logger.info("------- " + d.getClass().getSimpleName() + " -------");
            List<String> inputs = null;
            if (!(testMode == TestMode.TEST_ONLY)) {
                inputs = InputReader.readLines(d.getClass().getSimpleName().toLowerCase());
            }
            if (!(testMode == TestMode.TASK_ONLY)) {
                Logger.info("Testing Part 1");
                for (int i = 0; i < d.testsPartOne.size(); i++) {
                    Timer test = new Timer();
                    test.start();
                    d.part1(d.testsPartOne.get(i), d.test1Params.get(i));
                    test.stop();
                    Logger.info("Test " + i +" took "+test.getRuntime());
                }
            }
            if (!(testMode == TestMode.TEST_ONLY)) {
                Logger.info("Part 1");
                Timer task = new Timer();
                task.start();
                d.part1(inputs, d.task1Params);
                task.stop();
                Logger.info("Task Part 1 took "+task.getRuntime());
            }
            if (!(testMode == TestMode.TASK_ONLY)) {
                Logger.info("Testing Part 2");
                for (int i = 0; i < d.testsPartTwo.size(); i++) {
                    Timer test = new Timer();
                    test.start();
                    d.part2(d.testsPartTwo.get(i), d.test2Params.get(i));
                    test.stop();
                    Logger.info("Test " + i +" took "+test.getRuntime());
                }
            }
            if (!(testMode == TestMode.TEST_ONLY)) {
                Logger.info("Part 2");
                Timer test = new Timer();
                test.start();
                d.part2(inputs, d.task2Params);
                test.stop();
                Logger.info("Task Part 2 took "+test.getRuntime());
            }
        }
    }

    public enum TestMode {
        TEST_ONLY, TASK_ONLY, BOTH
    }

    public enum RunMode{
        LATEST, ALL
    }
}



