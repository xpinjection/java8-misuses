package org.javaday.java8.misused.lambda.library_methods;

import org.javaday.java8.misused.User;
import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.HashMap;
import java.util.Map;

public class MapForEach {

    @Ugly
    class UsingOldGoodEntrySet{

        public Map<String, String> getUserNames(Map<String, User> users){
            Map<String, String> userNames = new HashMap<>();
            users.entrySet().forEach(u -> userNames.put(u.getKey(), u.getValue().getName()));
            return userNames;
        }

    }

    @Good
    class UsingMapForEach{

        public Map<String, String> getUserNames(Map<String, User> users){
            Map<String, String> userNames = new HashMap<>();
            users.forEach((key, value) -> userNames.put(key, value.getName()));
            return userNames;
        }

    }

}
