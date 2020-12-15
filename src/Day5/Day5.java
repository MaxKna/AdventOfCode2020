package Day5;

import util.Day;
import util.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 extends Day {

    public Day5(){
        super(5);
        this.registerTestPartOne("FBFBBFFRLR\nBFFFBBFRRR\n" +
                "FFFBBBFRRR\n" +
                "BBFFBBFRLL\n");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        int max = -1;
        for(String in : inputs){
           int seatId = analyzeBoardingPass(in);
           if(seatId > max){
               max = seatId;
           }
        }
        Logger.result("Maximum Seat ID: " + max);
    }

    public int analyzeBoardingPass(String in){
        int maxRow = 127;
        int minRow = 0;

        for(int i = 0; i< 7; i++){
            switch (in.charAt(i)){
                case 'F':
                    maxRow -= (maxRow-minRow+1)/2;
                    break;
                case 'B':
                    minRow += (maxRow-minRow+1)/2;
                    break;
            }
        }

        int maxSeat = 7;
        int minSeat = 0;

        for(int i = 0; i< 3; i++){
            switch (in.charAt(i+7)){
                case 'R':
                    minSeat +=(maxSeat-minSeat+1)/2;
                    break;
                case 'L':
                    maxSeat -=(maxSeat-minSeat+1)/2;
                    break;
            }
        }
        int seatID = minRow * 8 + minSeat;

        Logger.debug("Row: " + minRow+" Seat: "+minSeat +" SeatID: "+seatID);
        return seatID;
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        List<Integer> range = IntStream.rangeClosed(1,900).boxed().collect(Collectors.toList());
        for(String in : inputs){
            range.remove((Integer) analyzeBoardingPass(in));
        }
        Logger.result(range);
    }
}
