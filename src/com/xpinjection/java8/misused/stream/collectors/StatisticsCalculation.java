package com.xpinjection.java8.misused.stream.collectors;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.summarizingInt;

public class StatisticsCalculation {
    @Ugly
    class IterateThroughValuesSeveralTimes {
        public void printNameStats(List<User> users) {
            getNameLengthStream(users)
                    .max()
                    .ifPresent(max -> System.out.println("MAX: " + max));
            getNameLengthStream(users)
                    .min()
                    .ifPresent(min -> System.out.println("MIN: " + min));
        }

        private IntStream getNameLengthStream(List<User> users) {
            return users.stream()
                    .mapToInt(user -> user.getName().length());
        }
    }

    @Good
    class CalculateStatisticsInSingleRunWithCollector {
        public void registerUsers(List<User> users) {
            IntSummaryStatistics statistics = users.stream()
                    .collect(summarizingInt(user -> user.getName().length()));
            System.out.println("MAX: " + statistics.getMax());
            System.out.println("MIN: " + statistics.getMin());
        }
    }
}
