// ============================================================================
//  File          : ReturnPartOfOptional
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.misuses.optional;

import com.xpinjection.misuses.User;

import java.util.Optional;

/**
 * @author Alimenkou Mikalai
 * @version 1.0
 */
public class ReturnPartOfOptional {
    public class Misuse {
        public Optional<String> getUserName(Long userId) {
            Optional<User> user = findById(userId);
            return user.isPresent() ? Optional.of(user.get().getName())
                    : Optional.<String>empty();
        }
    }

    public class Correct {
        public Optional<String> getUserName(Long userId) {
            return findById(userId).map(User::getName);
        }
    }

    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai"));
    }
}
