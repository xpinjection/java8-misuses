package com.xpinjection.java8.good.builder2;

/**
 * @author Oleksandr Shkurko
 */
public class Sample {

    public static void main(String[] args) {
        User user = User.builder()
                .setId(1)
                .setName("guest")
                .setAge(18)
                .addRole("GUEST")
                .addRole("LOGIN")
                .build();

        System.out.println(user);
    }
}
