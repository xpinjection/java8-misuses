package org.javaday.java8.misused.lambda.library_methods;

import org.javaday.java8.misused.User;
import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.List;

import static java.util.Comparator.comparing;

public class Sorting {

    @Ugly
    class UsingCustomComparator{

        public void sortUsersById(List<User> users){
            users.sort((x, y) -> Long.compare(x.getId(), y.getId()));
        }

    }

    @Good
    class UsingExistingPredefinedComparator{

        public void sortUsersById(List<User> users){
            users.sort(comparing(User::getId));
        }

    }

}
