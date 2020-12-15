package Day7;

import java.util.*;

public class Bag {

    private static final Map<String, Bag> allBags = new HashMap<>();

    public static Bag getBag(String name){
        Bag b = allBags.get(name);
        if(b == null){
            b = new Bag(name);
            allBags.put(name,b);
        }
        return b;
    }

    private Bag(String name){
        this.name = name;
    }

    private final List<Bag> possibleParents = new ArrayList<>();
    private final Map<Bag,Integer> children = new HashMap<>();
    private final String name;

    public void addChildren(String name, int amount){
        Bag child = getBag(name);
        this.children.put(child,amount);
        child.addParent(this);
    }

    private void addParent(Bag b){
        this.possibleParents.add(b);
    }

    public String getName() {
        return name;
    }

    public static int getNumberOfPossibleOuterBags(String name){
        Bag b = getBag(name);
        Set<Bag> possibleParents = new HashSet<>();
        List<Bag> toCheck = new ArrayList<>(b.possibleParents);
        while(!toCheck.isEmpty()) {
            List<Bag> checkNext = new ArrayList<>();
            for (Bag parent : toCheck) {
                possibleParents.add(parent);
                checkNext.addAll(parent.possibleParents);
                //System.out.println("Possbile parent "+ parent.getName());
            }
            toCheck.clear();
            toCheck.addAll(checkNext);
        }
        return possibleParents.size();
    }

    public static int getNumberOfInnerBags(String name){
        Bag b = getBag(name);
        int sum = 0;
        for(Bag child : b.children.keySet()){
            sum += b.children.get(child);
            int childs = b.children.get(child) * getNumberOfInnerBags(child.getName());
            sum+= childs;
        }
        //System.out.println(name +" contains "+ sum +" children");

        return sum;
    }

    public static void reset(){
        allBags.clear();
    }

    public String toString(){
        return name;
    }
}
