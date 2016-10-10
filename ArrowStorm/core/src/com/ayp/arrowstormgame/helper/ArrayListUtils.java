package com.ayp.arrowstormgame.helper;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class ArrayListUtils {
    public static ArrayList<Integer> removeDuplicateIndex(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        for (Integer item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
}
