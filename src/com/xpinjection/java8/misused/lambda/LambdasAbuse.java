package com.xpinjection.java8.misused.lambda;

import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;

import java.util.Optional;
import org.apache.commons.lang.StringUtils;

public class LambdasAbuse {
    @Bad
    class UnneededLambdasUsage {
        public void processAndPrint(String name) {
            Optional.ofNullable(name)
                    .filter(s -> !s.isEmpty())
                    .map(s -> s.toUpperCase())
                    .map(s -> doProcess(s))
                    .ifPresent(s -> System.out.print(s));
        }
    }

    String doProcess(String name) {
        return "MR. " + name;
    }

    @Good
    class MethodReferenceUsage {
        public void processAndPrint(String name) {
            Optional.ofNullable(name)
                    .filter(StringUtils::isNotEmpty)        // replace with appropriate library method ref
                    .map(String::toUpperCase)               // replace with class method ref
                    .map(LambdasAbuse.this::doProcess)      // replace with instance method ref
                    .ifPresent(System.out::print);          // replace with static method ref
        }
    }
}