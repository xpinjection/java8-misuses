package com.xpinjection.java8.misused.stream.collectors;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class TrueFunctionalApproach {
    @Ugly
    class BeforeJava8 {
        public User findUsersWithMostRoles(List<User> users) {
            if (users.isEmpty()) {
                return null;
            }
            User mostPowerful = users.iterator().next();
            for (User user : users) {
                if (user.getRoles().size() > mostPowerful.getRoles().size()) {
                    mostPowerful = user;
                }
            }
            return mostPowerful;
        }
    }

    @Ugly
    class NaiveStreamsApproach {
        public User findUsersWithMostRoles(List<User> users) {
            return users.stream()
                    .sorted(comparingInt(u -> u.getRoles().size()))
                    .findFirst()
                    .orElse(null);
        }
    }

    @Ugly
    class StreamsWithReduction {
        public User findUsersWithMostRoles(List<User> users) {
            return users.stream()
                    .reduce((u1, u2) ->
                            u1.getRoles().size() > u2.getRoles().size() ? u1 : u2)
                    .orElse(null);
        }
    }

    @Good
    class MaxWithComparator {
        public User findUsersWithMostRoles(List<User> users) {
            return users.stream()
                    .max(comparingInt(u -> u.getRoles().size()))
                    .orElse(null);
        }
    }
}
