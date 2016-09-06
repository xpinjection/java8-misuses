// ============================================================================
//  File          : InternalOptionalUsage
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.java8.misused.optional;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;
import com.xpinjection.java8.misused.User;

import java.util.Optional;

public class InternalOptionalUsage {
    @Ugly
    class UnclearOptionalDependencyWithCheckForNull {
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

    @Good
    class ValidInternalOptionalDependency {
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
