// ============================================================================
//  File          : CommonChecksAsMethodReferences
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package org.javaday.java8.misused.stream;

import org.javaday.java8.misused.Permission;
import org.javaday.java8.misused.Role;
import org.javaday.java8.misused.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: Compare with "ImparativeCodeMix" example and either mix them or leave one of them
public class CommonChecksAsMethodReferences {

    private static final String ADMIN_ROLE = "admin";

    private final List<User> users = new ArrayList<>();

    public class Misuse {
        public boolean hasAdmin() {
            return users.stream()
                    .map(u -> {
                        if (u == null) {
                            throw new NullPointerException();
                        }
                        return u;
                    })
                    .flatMap(u -> u.getRoles().stream())
                    .map(Role::getName)
                    .anyMatch(name -> ADMIN_ROLE.equals(name));
        }
    }

    public class Correct {
        public boolean hasAdmin(Permission permission) {
            return users.stream()
                    .map(Objects::requireNonNull)
                    .flatMap(u -> u.getRoles().stream())
                    .map(Role::getName)
                    .anyMatch(ADMIN_ROLE::equals);
        }
    }
}
