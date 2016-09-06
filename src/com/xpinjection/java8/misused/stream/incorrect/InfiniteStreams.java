package com.xpinjection.java8.misused.stream.incorrect;

import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;

import java.util.stream.IntStream;

public class InfiniteStreams {
    @Bad
    public void infinite(){
        IntStream.iterate(0, i -> i + 1)
                .forEach(System.out::println);
    }

    @Good
    public void validOne(){
        IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(System.out::println);
    }

    @Bad
    public void stillInfinite(){
        IntStream.iterate(0, i -> ( i + 1 ) % 2)
                .distinct()
                .limit(10)
                .forEach(System.out::println);
    }

    @Good
    public void butThisOneIfFine(){
        IntStream.iterate(0, i -> ( i + 1 ) % 2)
                .limit(10)
                .distinct()
                .forEach(System.out::println);
    }
}
