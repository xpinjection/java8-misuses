package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations;
import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class DoNotNeglectDataStructures {

    @Ugly
    class UnnecessaryUseOfNestedStreamOperations {

        public List<Order> filterOrdersByStatuses(List<Order> orders, Set<Status> appropriateStatuses) {
            return orders.stream()
                    .filter(order ->
                            appropriateStatuses.stream().anyMatch(status ->
                                    status.equals(order.getStatus())))
                    .collect(toList());
        }
    }

    @Good
    class UseOfDataStructure {

        public List<Order> filterOrdersByStatuses(List<Order> orders, Set<Status> appropriateStatuses) {
            return orders.stream()
                    .filter(order -> appropriateStatuses.contains(order.getStatus()))
                    .collect(toList());
        }
    }

    class Order{
        public Status getStatus(){
            return null; //stub
        }
        //some other code...
    }

    class Status{
        //some other code...
    }
}
