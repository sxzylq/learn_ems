package org.dcits.service;

import org.dcits.entity.User;

public interface UserService {

    void save(User user);

    User login(User user) throws Exception;
}
