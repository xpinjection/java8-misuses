package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.User;

import java.util.List;

import static com.xpinjection.java8.misused.Annotations.Good;
import static com.xpinjection.java8.misused.Annotations.Ugly;

public class SkipAndLimitOnListIsWaste {
    @Ugly
    class SkipSomeElementsAndThenTakeSomeForProcessing {
        public void registerUsers(List<User> users) {
            users.stream().skip(5).limit(10)
                    .forEach(SkipAndLimitOnListIsWaste.this::registerUser);
        }
    }

    @Good
    class SublistDoNotWasteProcessingTime {
        public void registerUsers(List<User> users) {
            users.subList(5, 15)
                    .forEach(SkipAndLimitOnListIsWaste.this::registerUser);
        }
    }

    private void registerUser(User user) {
        //register user
    }
}
