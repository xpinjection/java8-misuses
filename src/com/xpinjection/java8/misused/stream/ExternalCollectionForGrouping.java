// ============================================================================
//  File          : ExternalCollectionForGrouping
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations;
import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.Permission;
import com.xpinjection.java8.misused.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

//TODO: Think whether we can simplify it in any way
public class ExternalCollectionForGrouping {

    private final Set<User> users = new HashSet<>();

    @Ugly
    //TODO: Come up with proper name
    public class Misuse {

        public Map<String, Set<User>> findEditors() {
            Map<String, Set<User>> editors = new HashMap<>();
            //@todo<lumii> is it better to use Multiset and avoid code in lambda
            users.forEach(u -> u.getRoles().stream()
                    .filter(r -> r.getPermissions().contains(Permission.EDIT))
                    .forEach(r -> {
                        Set<User> usersInRole = editors.get(r.getName());
                        if (usersInRole == null) {
                            usersInRole = new HashSet<User>();
                            editors.put(r.getName(), usersInRole);
                        }
                        usersInRole.add(u);
                    })
            );
            return editors;
        }
    }

    @Good
    //TODO: Come up with proper name
    public class Correct {

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
    private class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
