// ============================================================================
//  File          : ManualCollection
//  Created       : 31.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ManualCollection {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class ResultsAreAggregatedInExternalCollection {
        public List<String> getUserNames() {
            List<String> names = new ArrayList<>();
            users.stream().map(User::getName).forEach(names::add);
            return names;
        }
    }

    @Good
    class ResultsAreCollectedWithStreamCollectors {
        public List<String> getUserNames() {
            return users.stream().map(User::getName).collect(toList());
        }
    }
}
