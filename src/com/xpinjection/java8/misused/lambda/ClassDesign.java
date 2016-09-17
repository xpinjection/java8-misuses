package com.xpinjection.java8.misused.lambda;

import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ClassDesign {
    @Bad
    static class AmbiguousOverloadedMethods {
        interface AmbiguousService<T> {
            <R> R process(Function<T, R> fn);

            T process(UnaryOperator<T> fn);
        }

        public void usage(AmbiguousService<String> service) {
            //which method you intended to call??? both are acceptable.
            service.process(String::toUpperCase);
        }
    }

    @Good
    static class SeparateSpecializedMethods {
        interface ClearService<T> {
            <R> R convert(Function<T, R> fn);

            T process(UnaryOperator<T> fn);
        }

        public void usage(ClearService<String> service) {
            //now it's clear which method will be called.
            service.convert(String::toUpperCase);
        }
    }
}
