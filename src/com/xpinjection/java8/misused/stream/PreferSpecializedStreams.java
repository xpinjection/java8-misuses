// ============================================================================
//  File          : PreferSpecializedStreams
//  Created       : 31.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations;
import com.xpinjection.java8.misused.Annotations.Bad;
import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.Set;

public class PreferSpecializedStreams {

    private final Set<User> users = new HashSet<>();

    @Bad
    public class GeneralStreamUsage {

        public int getAverageAge() {
            return users.stream()
                    .map(User::getAge)
                    .reduce(0, Integer::sum);
        }
    }

    @Good
    public class SpecializedStreamUsage {

        public int getAverageAge() {
            return users.stream()
                    .mapToInt(User::getAge)
                    .sum();
        }
    }
}
