package com.xpinjection.java8.misused.optional;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.Optional;

public class StrictCheckOfValuePresence {
    @Ugly
    class ManualCheckForPresenceToThrowException {
        public String getUserName(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                return user.get().getName();
            }
            throw new IllegalStateException("User not found");
        }

        public void deleteUser(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                delete(user.get());
            }
        }

        private void delete(User user) {
            //delete from DB
        }
    }

    @Good
    class OrElseThrowUsage {
        public String getUserName(Long userId) {
            return findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User not found"))
                    .getName();
        }

        public void deleteUser(Long userId) {
            findById(userId).ifPresent(this::delete);
        }

        private void delete(User user) {
            //delete from DB
        }
    }

    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai", 33));
    }
}
