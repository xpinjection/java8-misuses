// ============================================================================
//  File          : NullableDependency
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

//TODO: I would rename to "InternalOptionalUsage", because it's what we want to emphasize here.
public class NullableDependency {

    class Misuse {

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

    class Correct {

        private Optional<Printer> printer = Optional.empty();

        public void process(User user) {
            //some processing
            printer.ifPresent(p -> p.print(user));
        }

        public void setPrinter(Printer printer) {
            this.printer = Optional.ofNullable(printer);
        }
    }

    interface Printer {
        void print(User user);
    }
}
