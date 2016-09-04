package org.javaday.java8.misused.optional;

import com.sun.istack.internal.Nullable;
import org.javaday.java8.misused.User;
import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.Optional;

import static java.util.Optional.ofNullable;

//TODO: Kolya, check this example as we missed it during our discussion
public class WrapIntoOptional {

    @Ugly
    class WrapToCheckIfPresent{

        public void processUser(@Nullable User user){
            ofNullable(user).ifPresent(u -> {
                //Process User
            });
        }

    }

    @Good
    class SimpleIfStillAlive{

        public void processUser(@Nullable User user){
            if (user != null) {
                //Process User
            }
        }

    }

    @Good
    class IfPresentWithOptional{

        public void processUser(){
            Optional<User> user = getUser();
            user.ifPresent(u -> {
                //Process User
            });
        }

        public Optional<User> getUser(){
            return Optional.empty();
        }

    }

}
