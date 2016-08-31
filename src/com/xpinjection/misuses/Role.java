// ============================================================================
//  File          : Role
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package com.xpinjection.misuses;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author Alimenkou Mikalai
 * @version 1.0
 */
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

    public void setPermissions(EnumSet<Permission> permissions) {
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
