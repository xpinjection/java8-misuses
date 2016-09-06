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

import com.xpinjection.java8.misused.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

//TODO: I would delete this example as other examples () cover this case already
public class ManualCollection {

    private final Set<User> users = new HashSet<>();

    public class Misuse {
        public List<String> getUserNames() {
            List<String> names = new ArrayList<>();
            users.stream().map(User::getName).forEach(names::add);
            return names;
        }
    }

    public class Correct {
        public List<String> getUserNames() {
            return users.stream().map(User::getName).collect(toList());
        }
    }
}
