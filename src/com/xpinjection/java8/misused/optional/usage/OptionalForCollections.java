package com.xpinjection.java8.misused.optional.usage;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalForCollections {
    private static final String ADMIN_ROLE = "admin";

    @Ugly
    class TooVerbose {
        public User findAnyAdmin() {
            Optional<List<User>> users = findUsersByRole(ADMIN_ROLE);
            if (users.isPresent() && !users.get().isEmpty()) {
                return users.get().get(0);
            }
            throw new IllegalStateException("No admins found");
        }

        private Optional<List<User>> findUsersByRole(String role) {
            //real search in DB
            return Optional.empty();
        }
    }

    @Good
    class NiceAndClean {
        public User findAnyAdmin() {
            return findUsersByRole(ADMIN_ROLE).stream()
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("No admins found"));
        }

        private List<User> findUsersByRole(String role) {
            //real search in DB
            return Collections.emptyList();
        }
    }
}
