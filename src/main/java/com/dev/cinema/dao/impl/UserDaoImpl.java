package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.User;
import java.util.List;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public List getAll(Class clazz) {
        return getAll(User.class);
    }
}
