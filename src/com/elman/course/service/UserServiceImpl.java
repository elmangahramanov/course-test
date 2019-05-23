package com.elman.course.service;

import com.elman.course.dao.UserDao;
import com.elman.course.exceptions.DuplicateEmailException;
import com.elman.course.model.Role;
import com.elman.course.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Role> getAllRoleType() {
        return userDao.getAllRoleType();
    }

    @Override
    public boolean registerUser(User user) throws DuplicateEmailException {
        return userDao.registerUser(user);
    }
}
