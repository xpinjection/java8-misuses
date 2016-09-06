package com.xpinjection.java8.misused.stream.incorrect;

import com.xpinjection.java8.misused.Annotations.Bad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UseStreamMoreThanOnce {
    @Bad
    public void streamIsClosedAfterTerminalOperation() {
        int[] array = new int[]{1, 2};
        IntStream stream = Arrays.stream(array);
        stream.forEach(System.out::println);
        array[0] = 2;
        stream.forEach(System.out::println);
        //IllegalStateException: stream has already been operated upon or closed
    }
}
