package org.javaday.java8.misused.stream.incorrect;

import java.util.stream.IntStream;

public class InfiniteStreams {

    public void infinite(){
        IntStream.iterate(0, i -> i + 1)
                .forEach(System.out::println);
    }

    public void validOne(){
        IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(System.out::println);
    }

    public void stillInfinite(){
        IntStream.iterate(0, i -> ( i + 1 ) % 2)
                .distinct()
                .limit(10)
                .forEach(System.out::println);
    }

    public void butThisOneIfFine(){
        IntStream.iterate(0, i -> ( i + 1 ) % 2)
                .limit(10)
                .distinct()
                .forEach(System.out::println);
    }

}
