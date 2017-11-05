package com.xpinjection.java8.misused.lambda;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.Set;
import java.util.function.Supplier;

public class LazyCalculationsImprovePerformance {
    @Ugly
    static class LoggingWithAdditionalCheckToAvoidCalculations {
        private static final Log LOG = null; // init logger with factory

        public void sendWelcomeEmailToUsers(Set<User> users) {
            // send email
            if (LOG.isDebugEnabled()) {
                LOG.debug("Emails have been sent for users: " + users);
            }
        }

        interface Log {
            void debug(String message);

            boolean isDebugEnabled();
        }
    }

    @Good
    static class PassLambdaToLazyCalculateValueForLogMessage {
        private static final Log LOG = null; // init logger with factory

        public void sendWelcomeEmailToUsers(Set<User> users) {
            // send email
            LOG.debug(() -> "Emails have been sent for users: " + users);
        }

        interface Log {
            void debug(String message);

            boolean isDebugEnabled();

            default void debug(Supplier<String> message) {
                if (isDebugEnabled()) {
                    debug(message.get());
                }
            }
        }
    }
}
