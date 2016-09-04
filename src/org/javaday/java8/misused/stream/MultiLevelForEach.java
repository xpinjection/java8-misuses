// ============================================================================
//  File          : MultiLevelForEach
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
import java.util.concurrent.atomic.AtomicBoolean;

//TODO: I would delete this example as "HundredAndOneApproach", "NestedForEach" and "DoNotNeglectDataStructures" examples cover this case
public class MultiLevelForEach {

    private final Set<User> users = new HashSet<>();

    public class Misuse {
        public boolean checkPermission(Permission permission) {
            AtomicBoolean found = new AtomicBoolean();
            //@todo<lumii> bad sample, need more realistic
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

    public class Correct {
        public boolean checkPermission(Permission permission) {
            return users.stream().flatMap(u -> u.getRoles().stream())
                    .flatMap(r -> r.getPermissions().stream())
                    .anyMatch(permission::equals);
        }
    }
}
