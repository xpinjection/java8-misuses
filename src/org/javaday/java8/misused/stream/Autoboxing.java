// ============================================================================
//  File          : Autoboxing
//  Created       : 31.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package org.javaday.java8.misused.stream;

import org.javaday.java8.misused.User;

import java.util.HashSet;
import java.util.Set;

//TODO: I would use diff name here like "PreferSpecializedStreams"
public class Autoboxing {

    private final Set<User> users = new HashSet<>();

    public class Misuse {
        public int getAverageAge() {
            return users.stream().map(User::getAge).reduce(0, Integer::sum);
        }
    }

    public class Correct {
        public int getAverageAge() {
            return users.stream().mapToInt(User::getAge).sum();
        }
    }
}
