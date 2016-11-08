package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.Permission;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExternalStateWithForEach {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class UseOldSchoolIterationsWithForEachAndExternalBoolean {
        public boolean checkPermission(Permission permission) {
            AtomicBoolean found = new AtomicBoolean();
            users.forEach(
                    u -> u.getRoles().forEach(
                            r -> {
                                if (r.getPermissions().contains(permission)) {
                                    found.set(true);
                                }
                            }
                    )
            );
            return found.get();
        }
    }

    @Good
    class UseFlatMapForSubCollections {
        public boolean checkPermission(Permission permission) {
            return users.stream()
                    .flatMap(u -> u.getRoles().stream())
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
}
