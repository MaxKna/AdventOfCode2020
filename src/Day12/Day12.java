package Day12;

import util.Day;
import util.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 extends Day {

    public Day12(){
        super(12);
        this.registerTestBothParts("F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11");
    }


    @Override
    public void part1(List<String> inputs, List<Object> params) {
        List<Action> actionList = inputs.stream().map(in -> new Action(in.charAt(0),Integer.parseInt(in.substring(1)))).collect(Collectors.toList());
        Ship ship = new Ship();
        for(Action a : actionList){
            ship.executeMove(a);
            Logger.debug(ship);
        }
        Logger.result("Manhattan Distance: " + ship.getManhattanDist());
    }


    @Override
    public void part2(List<String> inputs, List<Object> params) {
        List<Action> actionList = inputs.stream().map(in -> new Action(in.charAt(0),Integer.parseInt(in.substring(1)))).collect(Collectors.toList());
        Ship ship = new Ship();
        Logger.debug(ship);
        for(Action a : actionList){
            ship.executeMove2(a);
            Logger.debug(ship);
        }
        Logger.result("Manhattan Distance: " + ship.getManhattanDist());
    }

    private static class Action{

        char type;
        int amount;

        public Action(char type, int amount) {
            this.type = type;
            this.amount = amount;
        }
    }

    private static class Ship{
        int x = 0;
        int y = 0;

        Direction direction = Direction.EAST;

        public void executeMove(Action action){
            switch (action.type){
                case 'N':
                    y += action.amount;
                    break;
                case 'S':
                    y -= action.amount;
                    break;
                case 'E':
                    x += action.amount;
                    break;
                case 'W':
                    x -= action.amount;
                    break;
                case 'L':
                    direction = direction.addDirection(-action.amount);
                    break;
                case 'R':
                    direction = direction.addDirection(action.amount);
                    break;
                case 'F':
                    switch (direction){
                        case NORTH:
                            y+= action.amount;
                            break;
                        case SOUTH:
                            y -= action.amount;
                            break;
                        case EAST:
                            x += action.amount;
                            break;
                        case WEST:
                            x -= action.amount;
                            break;
                    }
            }
        }

        private final Waypoint waypoint = new Waypoint();

        public void executeMove2(Action action){
            switch (action.type){
                case 'N':
                    waypoint.y += action.amount;
                    break;
                case 'S':
                    waypoint.y -= action.amount;
                    break;
                case 'E':
                    waypoint.x += action.amount;
                    break;
                case 'W':
                    waypoint.x -= action.amount;
                    break;
                case 'L':
                    waypoint.rotate(true, action.amount/90);
                    break;
                case 'R':
                    waypoint.rotate(false, action.amount/90);
                    break;
                case 'F':
                    this.x += (waypoint.x * action.amount);
                    this.y += (waypoint.y * action.amount);
                    break;
            }
        }

        public int getManhattanDist(){
            return Math.abs(x)+Math.abs(y);
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(x >= 0){
                sb.append("east ");
            }else{
                sb.append("west ");
            }
            sb.append(Math.abs(x));
            if(y >= 0){
                sb.append(", north ");
            }else{
                sb.append(", south ");
            }
            sb.append(Math.abs(y));
            sb.append(" facing ").append(direction);
            sb.append(" Waypoint: ").append(waypoint);

            return sb.toString();
        }
    }

    private enum Direction{

        NORTH(0), SOUTH(180), EAST(90), WEST(270);

        private final int degrees;

        Direction(int degrees){
            this.degrees = degrees;
        }

        public Direction addDirection(int degrees){
            int deg = this.degrees + degrees;
            if(deg < 0){
                deg += 360;
            }else if(deg >= 360){
                deg -= 360;
            }
            switch (deg){
                case 0: return NORTH;
                case 90: return EAST;
                case 180: return SOUTH;
                case 270: return WEST;
                default: return null;
            }
        }
    }

    private static class Waypoint{
        int x = 10;
        int y = 1;

        @SuppressWarnings("SuspiciousNameCombination")
        public void rotate(boolean left, int amount){
            for(int i = 0; i< amount; i++) {
                int preX = x;
                int preY = y;
                if (left) {
                    y = preX;
                    x = -preY;
                } else {
                    y = -preX;
                    x = preY;
                }
            }
        }

        public String toString(){
            return "X: "+ x +" Y: " + y;
        }
    }
}
