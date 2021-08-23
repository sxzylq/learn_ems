package org.dcits.service.imps;

import org.dcits.dao.UserDao;
import org.dcits.entity.User;
import org.dcits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImps implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        User byUserName = userDao.findByUserName(user.getUsername());
        if (user == null) throw  new RuntimeException("该用户已注册");
        user.setStatus("已激活");
        user.setRegisterTime(new Date());
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        User byUserName = userDao.findByUserName(user.getUsername());
        if(byUserName == null){
            throw new RuntimeException("用户名输入错误");
        }
        if (!user.getPassword().equals(byUserName.getPassword())){
            throw new RuntimeException("密码输入错误");
        }
        return byUserName;
    }
}
