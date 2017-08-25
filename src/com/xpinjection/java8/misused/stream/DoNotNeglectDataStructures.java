package com.xpinjection.java8.misused.stream;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class DoNotNeglectDataStructures {
    @Ugly
    class UnnecessaryUseOfNestedStreamOperations {
        public List<Order> filterOrdersByStatuses(List<Order> orders, Set<Status> appropriateStatuses) {
            return orders.stream()
                    .filter(order ->
                            appropriateStatuses.stream().anyMatch(order.getStatus()::equals))
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

    @Ugly
    class StateIsStoredInBadDataStructure {
        private final List<Order> orders = new ArrayList<>();

        public void placeOrder(Order order) {
            orders.add(order);
        }

        public List<Order> getOrdersInStatus(Status status) {
            return orders.stream()
                    .filter(order -> order.getStatus() == status)
                    .collect(toList());
        }
    }

    @Good
    class InternalDataStructureMayBeOptimizedForAccessMethods {
        //Use multimap instead from external collections like Guava
        private final Map<Status, List<Order>> orders = new EnumMap<>(Status.class);

        public void placeOrder(Order order) {
            orders.computeIfAbsent(order.getStatus(), status -> new ArrayList<>()).add(order);
        }

        public List<Order> getOrdersInStatus(Status status) {
            return orders.get(status);
        }
    }

    class Order {
        private Status status = Status.ACTIVE;

        Status getStatus() {
            return status;
        }

        void setStatus(Status status) {
            this.status = status;
        }
    }

    enum Status {
        ACTIVE, SUSPENDED, CLOSED
    }
}
