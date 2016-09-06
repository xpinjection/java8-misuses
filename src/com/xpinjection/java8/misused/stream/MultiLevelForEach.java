// ============================================================================
//  File          : MultiLevelForEach
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.Permission;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiLevelForEach {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class UseOldSchoolIterationsWithLeveledForEach {
        public boolean checkPermission(Permission permission) {
            AtomicBoolean found = new AtomicBoolean();
            //@todo<lumii> bad sample, need more realistic or merge with "NestedForEach"
            users.forEach(
                    u -> u.getRoles().forEach(
                            r -> r.getPermissions().forEach(
                                    p -> {
                                        if (p == permission) {
                                            found.set(true);
                                        }
                                    }
                            )
                    )
            );
            return found.get();
        }
    }

    @Good
    class UseFlatMapForSubCollections {
        public boolean checkPermission(Permission permission) {
            return users.stream().flatMap(u -> u.getRoles().stream())
                    .flatMap(r -> r.getPermissions().stream())
                    .anyMatch(permission::equals);
        }
    }
}
