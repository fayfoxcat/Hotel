package org.fox.dao;

import org.fox.entity.User;

public interface UserMapper {
    //新增用户
    public void addUser(User user);
    //用户登录
    public User queryUser(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user);
}
