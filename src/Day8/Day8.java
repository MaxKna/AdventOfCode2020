package Day8;

import util.Day;
import util.Logger;

import java.util.List;

public class Day8 extends Day {

    public Day8(){
        super(8);
        this.registerTestBothParts("nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        Handheld h = new Handheld();
        Logger.result(h.runProgramUntilLoop(inputs));
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        Handheld h = new Handheld();
        Logger.result(h.findRunningProgram(inputs));
    }
}
