package com.elman.course.model;

import java.util.List;

public class Action {
    private int id;
    private String action_type;
    private List<Role> roleList;

    public Action() {
    }

    public Action(int id, String action_type, List<Role> roleList) {
        this.id = id;
        this.action_type = action_type;
        this.roleList = roleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", action_type='" + action_type + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
