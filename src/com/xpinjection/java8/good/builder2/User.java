package com.xpinjection.java8.good.builder2;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

/**
 * @author Oleksandr Shkurko
 */
public class User {
    private Long id;
    private String name;
    private int age;
    private Set<String> roles;

    private User() {
    }

    public static UserBuilderId builder() {
        return id -> name -> age ->

                new UserBuilderRoles() {
                    @Override
                    public User build() {
                        User user = new User();
                        user.id = id;
                        user.name = requireNonNull(name);
                        user.age = age;
                        user.roles = unmodifiableSet(this.roles);
                        return user;
                    }
                };
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public interface UserBuilderId {
        UserBuilderName setId(long id);
    }

    public interface UserBuilderName {
        UserBuilderAge setName(String name);
    }

    public interface UserBuilderAge {
        UserBuilderRoles setAge(int age);
    }

    public static abstract class UserBuilderRoles {

        Set<String> roles = new HashSet<>();

        public UserBuilderRoles addRole(String role) {
            roles.add(requireNonNull(role));
            return this;
        }

        public abstract User build();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("roles=" + roles)
                .toString();
    }
}
