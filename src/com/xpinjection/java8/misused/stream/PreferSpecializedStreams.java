package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PreferSpecializedStreams {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class GeneralStreamUsage {
        public int getTotalAge() {
            return users.stream()
                    .map(User::getAge)
                    .reduce(0, Integer::sum);
        }
    }

    @Good
    class SpecializedStreamUsage {
        public int getTotalAge() {
            return users.stream()
                    .mapToInt(User::getAge)
                    .sum();
        }
    }

    @Ugly
    class FlatMapToCountElementsInAllCollections {
        public int countEmployees(Map<String, List<User>> departments) {
            return (int) departments.values().stream()
                    .flatMap(List::stream)
                    .count();
        }
    }

    @Good
    class MapToIntToSimplifyCalculation {
        public long countEmployees(Map<String, List<User>> departments) {
            return departments.values().stream()
                    .mapToInt(List::size)
                    .sum();
        }
    }
}
