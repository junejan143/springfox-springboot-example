package com.gemini.service;

import com.gemini.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author 章源辰
 * @time: 2016/11/2 17:02
 * @describion:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public void createUser(User user) {
        System.out.println("创建用户成功");
    }

    @Override
    public User getUser(int userId) {
        User user = new User();
        user.setUserId(3);
        user.setName("张三");
        user.setSex("男");
        return user;
    }
}
