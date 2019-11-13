package com.xpinjection.java8.good.builder;

/**
 * @author Oleksandr Shkurko
 */
public class Sample {

    public static void main(String[] args) {
        User user = User.builder()
                .setId(42)
                .setName("Guest")
                .setAge(23)
                .build();

        System.out.println(user);
    }
}
