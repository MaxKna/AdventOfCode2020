package Day7;

import util.Day;
import util.Logger;

import java.util.List;

public class Day7 extends Day {

    public Day7(){
        super(7);
        this.registerTestPartOne("light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags.");

        this.registerTestPartTwo("shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags.");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        learnBagRules(inputs);
        String toFind = "shiny gold";
        Logger.result(toFind + " has " + Bag.getNumberOfPossibleOuterBags(toFind) + " possible parents");
    }

    private void learnBagRules(List<String> inputs){
        Bag.reset();
        for(String line : inputs){
            String[] parts = line.split(" +");
            Bag parent = Bag.getBag(parts[0]+" "+parts[1]);
            int numbOfChilden = (parts.length-4)/4;
            for(int i = 1; i<= numbOfChilden; i++){
                try {
                    int amount = Integer.parseInt(parts[i * 4]);
                    String name = parts[i * 4 + 1]+" " + parts[i * 4 + 2];
                    Logger.debug("Bag colored '" + name + "' amount " + amount + " in " + parent.getName());
                    parent.addChildren(name, amount);
                }catch(NumberFormatException ignored){

                }
            }
        }
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        learnBagRules(inputs);
        String toFind = "shiny gold";
        Logger.result(toFind + " contains "+Bag.getNumberOfInnerBags(toFind)+" bags.");

    }
}
