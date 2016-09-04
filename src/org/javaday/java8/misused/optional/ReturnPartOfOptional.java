// ============================================================================
//  File          : ReturnPartOfOptional
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

//TODO: Compare with "OptionalElvis" and "WrapIntoOptional" examples and either mix them or leave some of them
public class ReturnPartOfOptional {

    class Misuse {
        public Optional<String> getUserName(Long userId) {
            Optional<User> user = findById(userId);
            return user.isPresent() ? Optional.of(user.get().getName()) : Optional.empty();
        }
    }

    class Correct {
        public Optional<String> getUserName(Long userId) {
            return findById(userId).map(User::getName);
        }
    }

    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai"));
    }
}
