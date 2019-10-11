package org.fox.service;

import org.fox.entity.User;

public interface UserService {
    //新增用户
    public int insertUser(User user);
    //用户登录
    public User loginUser(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user);
}
