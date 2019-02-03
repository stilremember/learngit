package org.springside.modules.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataUtils {
    private static Random random = new Random();

    public DataUtils() {
    }

    public static long randomId() {
        return random.nextLong();
    }

    public static String randomName(String prefix) {
        return prefix + random.nextInt(10000);
    }

    public static <T> T randomOne(List<T> list) {
        return randomSome(list, 1).get(0);
    }

    public static <T> List<T> randomSome(List<T> list) {
        return randomSome(list, random.nextInt(list.size()));
    }

    public static <T> List<T> randomSome(List<T> list, int count) {
        Collections.shuffle(list);
        return list.subList(0, count);
    }
}
