// ============================================================================
//  File          : StrictCheckOfValuePresence
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package org.javaday.java8.misused.optional;

import org.javaday.java8.misused.User;

import java.util.Optional;


public class StrictCheckOfValuePresence {

    class Misuse {
        public String getUserName(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                return user.get().getName();
            }
            throw new IllegalStateException("User not found");
        }
    }

    class Correct {
        public String getUserName(Long userId) {
            return findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User not found"))
                    .getName();
        }
    }

    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai"));
    }
}
