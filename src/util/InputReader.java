package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    private static String PREFIX = "resources/";
    private static String SUFFIX = ".txt";

    public static List<String> readLines(String filename){
        if(!filename.startsWith(PREFIX)){
            filename = PREFIX + filename;
        }
        if(!filename.endsWith(SUFFIX)){
            filename += SUFFIX;
        }
        List<String> ret = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filename))){
            while(sc.hasNextLine()){
                ret.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static List<String> readTest(String test){
        return new ArrayList<>(Arrays.asList(test.split("\n")));
    }
}

