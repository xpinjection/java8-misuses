package com.xpinjection.java8.misused.lambda.collections;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.Permission;
import com.xpinjection.java8.misused.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoveElementWithIterator {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class ManuallyRemoveElementWithIteratorRemove {
        public void removeUsersWithPermission(Permission permission) {
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getRoles().stream()
                        .anyMatch(r -> r.getPermissions().contains(permission))) {
                    iterator.remove();
                }
            }
        }
    }

    @Good
    class RemoveWithPredicate {
        public void removeUsersWithPermission(Permission permission) {
            users.removeIf(user -> user.getRoles().stream()
                    .anyMatch(r -> r.getPermissions().contains(permission)));
        }
    }
}