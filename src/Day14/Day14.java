package Day14;

import util.Day;
import util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends Day {

    public Day14() {
        super(14);
        this.registerTestPartOne("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0");
        this.registerTestPartTwo("mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        String mask = "";
        Memory m = new Memory();
        for (String line : inputs) {
            if (line.startsWith("mask")) {
                mask = line.split("=")[1].trim();
            } else {
                long value = Long.parseLong(line.split("=")[1].trim());
                long address = getAddress(line);
                String binary =getBinary(value);
                Logger.debug("Value: " + value + " address: " + address);
                Logger.debug(mask);
                Logger.debug(binary);
                long convValue = Long.parseLong(applyConversion(mask, binary), 2);
                Logger.debug("ConvValue: " + convValue);
                m.memory.put(address, convValue);
            }
        }
        Logger.debug(m.memory);
        Logger.result(m.getMemorySum());


    }

    private long getAddress(String line){
        String numPart = line.split("=")[0].split("\\[")[1];
        return Long.parseLong(numPart.substring(0, numPart.length() - 2));
    }

    private String getBinary(long decimal){
        String binary = Long.toBinaryString(decimal);
        binary = ("000000000000000000000000000000000000" + binary).substring(binary.length());
        return binary;
    }

    private String applyConversion(String mask, String bits) {
        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                processed.append(bits.charAt(i));
            } else if (mask.charAt(i) == '1' || mask.charAt(i) == '0') {
                processed.append(mask.charAt(i));
            }
        }
        return processed.toString();
    }

    private String applyConversionV2(String mask, String bits) {
        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                processed.append(mask.charAt(i));
            } else if (mask.charAt(i) == '1') {
                processed.append(mask.charAt(i));
            } else if (mask.charAt(i) == '0') {
                processed.append(bits.charAt(i));
            }
        }
        return processed.toString();
    }

    private List<String> resolveFloatingBits(String address) {
        return resolveFloatingBits(address, new ArrayList<>());
    }

    private List<String> resolveFloatingBits(String address, List<String> resolved) {
        int index = address.indexOf('X');
        if (index == -1) {
            resolved.add(address);
            return resolved;
        }
        String zero = address.substring(0, index) + "0" + address.substring(index + 1);
        String one = address.substring(0, index) + "1" + address.substring(index + 1);
        resolveFloatingBits(zero, resolved);
        resolveFloatingBits(one, resolved);


        return resolved;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        String mask = "";
        Memory m = new Memory();
        for (String line : inputs) {
            if (line.startsWith("mask")) {
                mask = line.split("=")[1].trim();
            } else {
                long value = Long.parseLong(line.split("=")[1].trim());
                long address = getAddress(line);
                String binary =getBinary(address);
                Logger.debug("Value: " + value + " address: " + address);
                Logger.debug("Binary:    " + binary);
                Logger.debug("Mask:      " + mask);
                String convValue = applyConversionV2(mask, binary);
                Logger.debug("ConvValue: " + convValue);
                List<Long> indices = new ArrayList<>();
                for (String s : resolveFloatingBits(convValue)) {
                    indices.add(Long.parseLong(s,2));
                }
                for(long l : indices) {
                    m.memory.put(l, value);
                }
            }
        }
        Logger.debug(m.memory);
        Logger.result(m.getMemorySum());
    }

    private static class Memory {

        public Map<Long, Long> memory = new HashMap<>();

        public long getMemorySum() {
            long sum = 0;
            for (long mem : memory.values()) {
                sum += mem;
            }
            return sum;
        }
    }
}
