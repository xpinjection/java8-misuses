package com.xpinjection.java8.misused.stream.collectors;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.Permission;
import com.xpinjection.java8.misused.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class ExternalCollectionForGrouping {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class ExternalStateIsUsedForStreamOperations {
        public Map<String, Set<User>> findEditors() {
            Map<String, Set<User>> editors = new HashMap<>();
            users.forEach(u -> u.getRoles().stream()
                    .filter(r -> r.getPermissions().contains(Permission.EDIT))
                    .forEach(r -> {
                        //is it better to use Multiset and avoid this complex code
                        Set<User> usersInRole = editors.get(r.getName());
                        if (usersInRole == null) {
                            usersInRole = new HashSet<>();
                            editors.put(r.getName(), usersInRole);
                        }
                        usersInRole.add(u);
                    })
            );
            return editors;
        }
    }

    @Good
    class TuplesAreUsedWhenStateIsNeededOnLaterPhase {
        public Map<String, Set<User>> findEditors() {
            return users.stream()
                    .flatMap(u -> u.getRoles().stream()
                        .filter(r -> r.getPermissions().contains(Permission.EDIT))
                        .map(r -> new Pair<>(r, u))
                    ).collect(groupingBy(p -> p.getKey().getName(),
                            mapping(Pair::getValue, toSet())));
        }
    }

    //any tuple implementation from 3rd party libraries
    class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }
    }
}
