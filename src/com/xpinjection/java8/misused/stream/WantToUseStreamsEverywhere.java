package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public class WantToUseStreamsEverywhere {
    @Ugly
    class UseStreamToBuildMap {
        public Map<String, Object> getJpaProperties() {
            return Stream.of(
                    new AbstractMap.SimpleEntry<>("hibernate.show_sql", "true"),
                    new AbstractMap.SimpleEntry<>("hibernate.format_sql", "true")
            ).collect(collectingAndThen(
                    toMap(Map.Entry::getKey, Map.Entry::getValue),
                    Collections::unmodifiableMap)
            );
        }
    }

    @Good
    class UseOldPlainMap {
        public Map<String, Object> getJpaProperties() {
            Map<String, Object> properties = new HashMap<>();
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            return Collections.unmodifiableMap(properties);
        }
    }
}
