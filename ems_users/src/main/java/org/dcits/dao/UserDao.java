package org.dcits.dao;

import org.dcits.entity.User;

public interface UserDao {

    void save(User user);

    User findByUserName(String name);
}
