package Day11;

import util.Day;
import util.Helpers;
import util.Logger;

import java.util.ArrayList;
import java.util.List;

public class Day11 extends Day {

    public Day11(){
        super(11);
        this.registerTestBothParts("L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL");
        this.registerTestPartTwo(".......#.\n" +
                "...#.....\n" +
                ".#.......\n" +
                ".........\n" +
                "..#L....#\n" +
                "....#....\n" +
                ".........\n" +
                "#........\n" +
                "...#.....");
        this.registerTestPartTwo(".............\n" +
                ".L.L.#.#.#.#.\n" +
                ".............");
        this.registerTestPartTwo(".##.##.\n" +
                "#.#.#.#\n" +
                "##...##\n" +
                "...L...\n" +
                "##...##\n" +
                "#.#.#.#\n" +
                ".##.##.");
    }

    private List<List<Character>> parseState(List<String> inputs){
        List<List<Character>> state = new ArrayList<>();
        for(String in : inputs){
            List<Character> line = new ArrayList<>();
            for(char c : in.toCharArray()){
                line.add(c);
            }
            state.add(line);
        }
        return state;
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        List<List<Character>> state = parseState(inputs);
        Logger.debug(printState(state));
        while(true){
            SeatRound sr = makeRound(state,1, 4);
            state = sr.state;
            if(sr.seatsChanged == 0){
                Logger.result("Occupied Seats: " + countOccupiedSeats(state));
                return;
            }
            Logger.debug(printState(state));
        }
    }

    private SeatRound makeRound(List<List<Character>> state, int part, int border){
        int seatsChanged = 0;
        List<List<Character>> copy = Helpers.deepCopyList(state);
        for(int x = 0; x < state.size(); x++){
            for(int y = 0; y < state.get(0).size(); y++){
                int adj = getNumberOfAdjacentSeats(part,state,x,y);
                char seat = state.get(x).get(y);
                if(seat == 'L' && adj == 0){
                    copy.get(x).set(y,'#');
                }else if(seat == '#' && adj >= border){
                    copy.get(x).set(y,'L');
                }
                if(state.get(x).get(y) != copy.get(x).get(y)){
                    seatsChanged++;
                }
            }
        }
        Logger.debug("Seats Changed: "+seatsChanged);
        return new SeatRound(copy, seatsChanged);
    }



    @SuppressWarnings("DuplicatedCode")
    private int getNumberOfAdjacentSeats(int part, List<List<Character>> state, int x, int y){
        int adj = 0;
        if(part == 1) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    try {
                        char c = state.get(x + i).get(y + j);
                        if (c == '#') {
                            adj++;
                        }
                    } catch (IndexOutOfBoundsException ignored) {

                    }
                }
            }
        }else if(part == 2){
            for(int nY = y+1; nY< state.get(0).size(); nY++){
                char c = state.get(x).get(nY);
                if(c == '.'){
                    continue;
                }else if(c == '#'){
                    adj++;
                }
                break;
            }
            for(int nY = y-1; nY >= 0; nY--){
                char c = state.get(x).get(nY);
                if(c == '.'){
                    continue;
                }else if(c == '#'){
                    adj++;
                }
                break;
            }
            for(int nX = x+1; nX< state.size(); nX++){
                char c = state.get(nX).get(y);
                if(c == '.'){
                    continue;
                }else if(c == '#'){
                    adj++;
                }
                break;
            }
            for(int nX = x-1; nX >= 0; nX--){
                char c = state.get(nX).get(y);
                if(c == '.'){
                    continue;
                }else if(c == '#'){
                    adj++;
                }
                break;
            }
            try {
                for (int nD = 1; nD < state.get(0).size(); nD++) {
                    char c = state.get(x+nD).get(y+nD);
                    if (c == '.') {
                        continue;
                    } else if (c == '#') {
                        adj++;
                    }
                    break;
                }
            }catch(IndexOutOfBoundsException ignored){

            }
            try {
                for (int nD = 1; nD < state.get(0).size(); nD++) {
                    char c = state.get(x+nD).get(y-nD);
                    if (c == '.') {
                        continue;
                    } else if (c == '#') {
                        adj++;
                    }
                    break;
                }
            }catch(IndexOutOfBoundsException ignored){

            }
            try {
                for (int nD = 1; nD < state.get(0).size(); nD++) {
                    char c = state.get(x-nD).get(y+nD);
                    if (c == '.') {
                        continue;
                    } else if (c == '#') {
                        adj++;
                    }
                    break;
                }
            }catch(IndexOutOfBoundsException ignored){

            }
            try {
                for (int nD = 1; nD < state.get(0).size(); nD++) {
                    char c = state.get(x-nD).get(y-nD);
                    if (c == '.') {
                        continue;
                    } else if (c == '#') {
                        adj++;
                    }
                    break;
                }
            }catch(IndexOutOfBoundsException ignored){

            }
        }
        return adj;
    }

    private int countOccupiedSeats(List<List<Character>> state){
        int count = 0;
        for (List<Character> characters : state) {
            for (int y = 0; y < state.get(0).size(); y++) {
                if (characters.get(y) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private String printState(List<List<Character>> state){
        StringBuilder sb = new StringBuilder();
        for (List<Character> characters : state) {
            for (int j = 0; j < state.get(0).size(); j++) {
                sb.append(characters.get(j));
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        List<List<Character>> state = parseState(inputs);

        while(true){
            SeatRound sr = makeRound(state,2, 5);
            state = sr.state;
            if(sr.seatsChanged == 0){
                Logger.result("Occupied Seats: "+countOccupiedSeats(state));
                return;
            }
            Logger.debug(printState(state));
        }
    }

    private static class SeatRound{

        public List<List<Character>> state;
        public int seatsChanged;

        public SeatRound(List<List<Character>> state, int seatsChanged) {
            this.state = state;
            this.seatsChanged = seatsChanged;
        }
    }
}
