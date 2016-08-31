// ============================================================================
//  File          : NullableDependency
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
public class NullableDependency {
    public class Misuse {
        private Printer printer;

        public void process(User user) {
            //some processing
            if (printer != null) {
                printer.print(user);
            }
        }

        public void setPrinter(Printer printer) {
            this.printer = printer;
        }
    }

    public class Correct {
        private Optional<Printer> printer = Optional.empty();

        public void process(User user) {
            //some processing
            printer.ifPresent(p -> p.print(user));
        }

        public void setPrinter(Printer printer) {
            this.printer = Optional.ofNullable(printer);
        }
    }

    public interface Printer {
        void print(User user);
    }
}
