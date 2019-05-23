package com.elman.course.model;

import java.io.Serializable;
import java.util.List;

public class Role {
    private int id;
    private String roleType;
    private List<Action> actionList;

    public Role() {
    }

    public Role(int id, String roleType, List<Action> actionList) {
        this.id = id;
        this.roleType = roleType;
        this.actionList = actionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String role_type) {
        this.roleType = role_type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
