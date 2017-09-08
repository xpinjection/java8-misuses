package com.xpinjection.java8.misused.stream.collectors;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.List;
import java.util.stream.Collectors;

public class StreamMayBeConvertedToArray {
    @Ugly
    class ConvertToArrayViaList {
        public String[] getUserNames(List<User> users) {
            List<String> names = users.stream()
                    .map(User::getName)
                    .collect(Collectors.toList());
            return names.toArray(new String[names.size()]);
        }
    }

    @Good
    class ConvertToArrayDirectly {
        public String[] getUserNames(List<User> users) {
            return users.stream()
                    .map(User::getName)
                    .toArray(String[]::new);
        }
    }
}
