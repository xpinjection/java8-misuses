package com.xpinjection.java8.misused.collections;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.List;

public class ProcessOnlySomeTypeInstances {
    @Ugly
    static class ForEachProcessing {
        public int calcNaNs(List list) {
            for (Object e : list) {
                int count = 0;
                if (e instanceof Double) {
                    Double d = (Double) e;
                    if (d.isNaN())
                        count++;
                }
            }
            return count;
        }
    }

    @Good
    static class SteamProcessing {
        public int calcNaNs(List list) {
            return list.stream()
                    .filter(Double.class::isInstance)   // instanceof steam equivalent
                    .mapToDouble(Double.class::cast)    // type cast steam equivalent
                    .filter(Double::isNaN)              // any type specific mathods can be used here
                    .count();
        }
    }
}