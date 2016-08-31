// ============================================================================
//  File          : Autoboxing
//  Created       : 31.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.misuses.stream;

import com.xpinjection.misuses.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alimenkou Mikalai
 * @version 1.0
 */
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
