// ============================================================================
//  File          : LongComplexLambda
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package org.javaday.java8.misused.stream;

import org.javaday.java8.misused.Permission;
import org.javaday.java8.misused.User;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

//TODO: Compare with "DoNotNeglectDataStructures" example and either mix them or leave one of them
public class LongComplexLambda {
    private final Set<User> users = new HashSet<>();

    public class Misuse {
        public Set<User> findEditors() {
            return users.stream()
                    .filter(u -> u.getRoles().stream()
                            .anyMatch(r -> r.getPermissions().contains(Permission.EDIT)))
                    .collect(toSet());
        }
    }

    public class Correct {
        public Set<User> checkPermission(Permission permission) {
            //@todo<lumii> add example with method reference
            return users.stream().filter(hasPermission(Permission.EDIT))
                    .collect(toSet());
        }

        private Predicate<User> hasPermission(Permission permission) {
            return u -> u.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
}
