package com.elman.course.dao;

import com.elman.course.exceptions.DuplicateEmailException;
import com.elman.course.model.Role;
import com.elman.course.model.User;

import java.util.List;

public interface UserDao {
    List<Role> getAllRoleType();
    boolean registerUser(User user) throws DuplicateEmailException;

}
