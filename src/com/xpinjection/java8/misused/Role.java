package com.xpinjection.java8.misused;

import java.util.EnumSet;
import java.util.Set;

public class Role {
    private String name;
    private Set<Permission> permissions = EnumSet.noneOf(Permission.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return name.equals(((Role) o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
