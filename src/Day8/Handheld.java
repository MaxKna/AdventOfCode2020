package Day8;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handheld {

    private int accumulator = 0;

    private int index = 0;

    private void executeLine(Instruction instruction){
        switch(instruction.command){
            case "acc":
                accumulator += instruction.param;
                index++;
                break;
            case "jmp":
                index += instruction.param;
                break;
            case "nop":
                index++;
                break;
        }
    }

    private void reset(){
        accumulator = 0;
        index = 0;
    }

    private Instruction parseLine(String input){
        String[] parts = input.split(" +");
        return new Instruction(parts[0],Integer.parseInt(parts[1]));
    }

    private List<Instruction> parseInputs(List<String> inputs){
        List<Instruction> out = new ArrayList<>();
        for(String in : inputs){
            out.add(parseLine(in));
        }
        return out;
    }

    public int runProgramUntilLoop(List<String> inputs){
        List<Instruction> instructions = parseInputs(inputs);
        findLoop(instructions);
        return accumulator;
    }

    public int findRunningProgram(List<String> inputs){
        List<Instruction> instructions = parseInputs(inputs);
        List<List<Instruction>> possibilities = getAlternateVersions(instructions);
        for(List<Instruction> possInst : possibilities){
            reset();
            if(!findLoop(possInst)){
                return accumulator;
            }
        }
        return -1;
    }

    private boolean findLoop(List<Instruction> instructions){
        Set<Integer> visitedIndices = new HashSet<>();
        try {
            while (true) {
                if (!visitedIndices.add(index)) {
                    System.out.println("Loop at " + index);
                    break;
                }
                executeLine(instructions.get(index));
            }
            return true;
        }catch(IndexOutOfBoundsException ioobe){
            System.out.println(ioobe.getMessage());
            return false;
        }
    }

    private List<List<Instruction>> getAlternateVersions(List<Instruction> instructions){
        List<List<Instruction>> out = new ArrayList<>();
        for(int i = 0; i< instructions.size(); i++){
            if(instructions.get(i).command.equals("acc")){
                continue;
            }
            List<Instruction> duplicate = new ArrayList<>();
            for(Instruction inst : instructions){
                duplicate.add((Instruction) inst.clone());
            }
            switch (duplicate.get(i).command){
                case "nop":
                    duplicate.get(i).command = "jmp";
                    break;
                case "jmp":
                    duplicate.get(i).command = "nop";
                    break;
            }
            out.add(duplicate);
        }
        return out;
    }
}

class Instruction{
    String command;
    int param;

    public Instruction(String command, int param) {
        this.command = command;
        this.param = param;
    }

    public String toString(){
        return command + " " + param;
    }

    @Override
    protected Object clone() {
        return new Instruction(command,param);
    }
}
