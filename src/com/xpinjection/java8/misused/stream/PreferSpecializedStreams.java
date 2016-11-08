package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.Set;

public class PreferSpecializedStreams {
    private final Set<User> users = new HashSet<>();

    @Bad
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
}
