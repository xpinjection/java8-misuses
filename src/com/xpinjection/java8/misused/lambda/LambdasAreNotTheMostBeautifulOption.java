package com.xpinjection.java8.misused.lambda;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.Optional;

public class LambdasAreNotTheMostBeautifulOption {
    @Ugly
    class UnneededLambdasUsage {
        public void processAndPrint(String name) {
            Optional.ofNullable(name)
                    //.filter(s -> !s.isEmpty())
                    .map(s -> s.toUpperCase())
                    .map(s -> doProcess(s))
                    .ifPresent(s -> System.out.print(s));
        }
    }

    @Good
    class MethodReferenceUsage {
        public void processAndPrint(String name) {
            Optional.ofNullable(name)
                    //.filter(StringUtils::isNotEmpty) // replace with appropriate library method ref
                    .map(String::toUpperCase)
                    .map(LambdasAreNotTheMostBeautifulOption.this::doProcess)
                    .ifPresent(System.out::print);
        }
    }

    private String doProcess(String name) {
        return "MR. " + name;
    }
}