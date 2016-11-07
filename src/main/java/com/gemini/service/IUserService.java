package com.gemini.service;

import com.gemini.domain.User;

/**
 * @author 章源辰
 * @time: 2016/11/2 17:01
 * @describion:
 */
public interface IUserService {

    void createUser(User user);

    User getUser(int userId);
}
