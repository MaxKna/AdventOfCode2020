package Day16;

import util.Day;
import util.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends Day {

    public Day16(){
        super(16);
        this.registerTestBothParts("class: 1-3 or 5-7\n" +
                "row: 6-11 or 33-44\n" +
                "seat: 13-40 or 45-50\n" +
                "\n" +
                "your ticket:\n" +
                "7,1,14\n" +
                "\n" +
                "nearby tickets:\n" +
                "7,3,47\n" +
                "40,4,50\n" +
                "55,2,20\n" +
                "38,6,12");
        this.registerTestPartTwo("class: 0-1 or 4-19\n" +
                "row: 0-5 or 8-19\n" +
                "seat: 0-13 or 16-19\n" +
                "\n" +
                "your ticket:\n" +
                "11,12,13\n" +
                "\n" +
                "nearby tickets:\n" +
                "3,9,18\n" +
                "15,1,5\n" +
                "5,14,9");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        Ticket ticket = parseTicketRules(inputs);
        List<Integer> myTicket = parseMyTicket(inputs);
        List<List<Integer>> nearbyTickets = parseNearbyTickets(inputs);


        Logger.debug(ticket);
        Logger.debug(myTicket);
        Logger.debug(nearbyTickets);

        List<Integer> breakingValues = new ArrayList<>();

        for(List<Integer> values : nearbyTickets){
            boolean valid = true;
            for(int value : values){
                boolean valueValid = ticket.checkIfAnyFit(value);
                if(!valueValid){
                    breakingValues.add(value);
                    valid = false;
                    break;
                }
            }
            Logger.debug(values +" "+valid);
        }
        Logger.debug(breakingValues);

        Logger.result(breakingValues.stream().mapToInt(i -> i).sum());

    }

    private static List<Integer> parseTicketRules(String line){
        return Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static Ticket parseTicketRules(List<String> inputs){
        Ticket ticket = new Ticket();
        for (String input : inputs) {
            if (input.equals("")) {
                break;
            } else {
                String[] parts = input.split(":");
                Field field = new Field();
                field.name = parts[0];
                String[] ranges = parts[1].split("or");
                String[] first = ranges[0].split("-");
                String[] second = ranges[1].split("-");
                field.min = Integer.parseInt(first[0].trim());
                field.max = Integer.parseInt(first[1].trim());
                field.min2 = Integer.parseInt(second[0].trim());
                field.max2 = Integer.parseInt(second[1].trim());
                ticket.fields.add(field);
            }
        }
        return ticket;
    }

    private List<Integer> parseMyTicket(List<String> inputs){
       return parseTicketRules(inputs.get(inputs.indexOf("your ticket:")+1));
    }

    private List<List<Integer>> parseNearbyTickets(List<String> inputs){
        List<List<Integer>> nearbyTickets = new ArrayList<>();
        int startIndex = inputs.indexOf("nearby tickets:")+1;
        for(int i = startIndex; i< inputs.size(); i++){
            nearbyTickets.add(parseTicketRules(inputs.get(i)));
        }
        return nearbyTickets;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        Ticket ticket = parseTicketRules(inputs);
        List<Integer> myTicket = parseMyTicket(inputs);
        List<List<Integer>> nearbyTickets = parseNearbyTickets(inputs);

        List<List<Integer>> toRemove = new ArrayList<>();

        for(List<Integer> values : nearbyTickets){
            for(int value : values){
                boolean valueValid = ticket.checkIfAnyFit(value);
                if(!valueValid){
                    toRemove.add(values);
                    break;
                }
            }
        }
        nearbyTickets.removeAll(toRemove);
        Logger.debug(nearbyTickets);

        Map<String,List<Integer>> indexMap = new HashMap<>();

        for(int i = 0; i< nearbyTickets.get(0).size(); i++){
            List<Integer> posValues = new ArrayList<>();
            for (List<Integer> nearbyTicket : nearbyTickets) {
                posValues.add(nearbyTicket.get(i));
            }
            int validCount = 0;
            String  lastValid = "";
            int lastValidValue = -1;
            for(Field f : ticket.fields){
                if(!indexMap.containsKey(f.name)){
                    indexMap.put(f.name,new ArrayList<>());
                }
                boolean valid = true;
                for(int value : posValues){
                    valid &= f.checkAnyFit(value);
                }
                if(valid){
                    indexMap.get(f.name).add(i);
                    validCount++;
                    lastValid = f.name;
                    lastValidValue = i;
                }
            }
            if(validCount == 1){
                indexMap.get(lastValid).clear();
                indexMap.get(lastValid).add(lastValidValue);
            }
        }

        boolean changed = true;
        while(changed){
            changed = false;
            for(String key : indexMap.keySet()){
                List<Integer> values = indexMap.get(key);
                if(values.size() == 1){
                    for(String k : indexMap.keySet()){
                        if(!k.equals(key) && indexMap.get(k).contains(values.get(0))) {
                            indexMap.get(k).remove(values.get(0));
                            changed = true;
                        }
                    }
                }
            }
        }

        for(String s : indexMap.keySet()){
            Logger.debug(s+" "+indexMap.get(s));
        }

        Map<String,Integer> indexMapReduced = indexMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));

        Map<String,Integer> myTicketMap = indexMapReduced.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> myTicket.get(e.getValue())));
        Logger.debug(myTicketMap);
        long multiplicand = 1;
        for(String s : myTicketMap.keySet()){
            if(s.startsWith("departure")){
                multiplicand *= myTicketMap.get(s);
                Logger.debug(s +" "+ myTicketMap.get(s));
            }
        }
        Logger.result(multiplicand);

    }

    private static class Field{
        String name;
        int min;
        int max;

        int min2;
        int max2;

        public String toString(){
            return name+": "+min+"-"+max+" or "+min2+"-"+max2;
        }

        public boolean checkAnyFit(int i){
            return i >= min && i <= max || i >= min2 && i<=max2;
        }
    }

    private static class Ticket{

        List<Field> fields = new ArrayList<>();

        public boolean checkIfAnyFit(int i){
            boolean b = false;
            for(Field f : fields){
                b |= f.checkAnyFit(i);
            }
            return b;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(Field f : fields){
                sb.append(f).append("\n");
            }
            return sb.toString();
        }
    }
}
