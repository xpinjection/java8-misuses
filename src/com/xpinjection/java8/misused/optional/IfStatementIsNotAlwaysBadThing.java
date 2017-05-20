package com.xpinjection.java8.misused.optional;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.Optional;
import java.util.stream.Stream;

public class IfStatementIsNotAlwaysBadThing {
    @Ugly
    class CombineSomeOptionalsInCleverWay {
        public Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            return Stream.of(first, second)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(Integer::sum);
        }
    }

    @Ugly
    class PlayMapGameInEvenMoreCleverWay {
        public Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            return first.map(b -> second.map(a -> b + a).orElse(b))
                    .map(Optional::of)
                    .orElse(second);
        }
    }

    @Good
    class OldSchoolButTotallyClearCode {
        public Optional<Integer> sum(Optional<Integer> first, Optional<Integer> second) {
            if (!first.isPresent() && !second.isPresent()) {
                return Optional.empty();
            }
            return Optional.of(first.orElse(0) + second.orElse(0));
        }
    }
}
