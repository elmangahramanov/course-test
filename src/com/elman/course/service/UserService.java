package com.elman.course.service;

import com.elman.course.exceptions.DuplicateEmailException;
import com.elman.course.model.Role;
import com.elman.course.model.User;

import java.util.List;

public interface UserService {
    List<Role> getAllRoleType();
    boolean registerUser(User user) throws DuplicateEmailException;
}
