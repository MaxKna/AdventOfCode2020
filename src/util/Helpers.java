package util;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static <E> List<List<E>> deepCopyList(List<List<E>> original){
        List<List<E>> copy = new ArrayList<>();
        for(List<E> list : original){
            copy.add(new ArrayList<>(list));
        }
        return copy;
    }
}
