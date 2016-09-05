package com.xpinjection.java8.misused.lambda;

import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ClassDesign {

    @Bad
    static class AmbiquousOverloadedMethods{

        interface AmbiquousService<T>{
            <R> R process(Function<T, R> fn);
            T process(UnaryOperator<T> fn);
        }

        public void usage(AmbiquousService<String> service){
            service.process(String::toUpperCase); //Which method you intended to call??? Both acceptable.
        }
    }

    @Good
    static class SeparateSpecializedMethods{

        interface NonAmbiquousService<T>{
            <R> R convert(Function<T, R> fn);
            T process(UnaryOperator<T> fn);
        }

        public void usage(NonAmbiquousService<String> service){
            service.convert(String::toUpperCase); //Now it's clear which method will be called.
        }
    }
}
