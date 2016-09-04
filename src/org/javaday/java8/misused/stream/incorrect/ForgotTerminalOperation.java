package org.javaday.java8.misused.stream.incorrect;

import java.util.stream.IntStream;

public class ForgotTerminalOperation {

    public void willDoNothing(){
        IntStream.range(1, 5)
                .peek(System.out::println)
                .peek(i -> {
                    if (i == 5)
                        throw new RuntimeException("bang");
                });
    }

}
