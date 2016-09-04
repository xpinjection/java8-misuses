package org.javaday.java8.misused.stream.incorrect;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UseStreamMoreThanOnce {

    public void useStreamMoreThanOnce() {
        int[] array = new int[]{1,2};
        IntStream stream = Arrays.stream(array);
        stream.forEach(System.out::println);
        array[0] = 2;
        stream.forEach(System.out::println);
    }

}
