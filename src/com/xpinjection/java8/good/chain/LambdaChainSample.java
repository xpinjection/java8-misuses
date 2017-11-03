package com.xpinjection.java8.good.chain;

import java.util.function.Function;

/**
 * @author Alimenkou Mikalai
 */
public class LambdaChainSample {
    public static void main(String[] args) {
        int digitsCount = startWith(String::trim)
                .andThen(String::toUpperCase)
                .andThen(wrap(counter())::count)
                .apply(" \n 123 \t");
        System.out.println(digitsCount + " digits found");
    }

    private static DigitCounter counter() {
        return new NaiveDigitCounter();
    }

    private static <T, R> Function<T, R> startWith(Function<T, R> function) {
        return function;
    }

    private static DigitCounter wrap(DigitCounter counter) {
        return s -> {
            long startTime = System.currentTimeMillis();
            int count = counter.count(s);
            long endTime = System.currentTimeMillis();
            System.out.println("Counting took " + (endTime - startTime) + " ms");
            return count;
        };
    }
}
