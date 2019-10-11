package org.fox.service.impl;

import org.fox.entity.User;
import org.fox.dao.UserMapper;
import org.fox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //新增用户
    @Override
    public int insertUser(User user) {
        if(userMapper.queryUser(user.getUsername()) == null){
            userMapper.addUser(user);
            if(userMapper.queryUser(user.getUsername())!=null){
                //新增成功
                return 1;
            }
        }else{
            //用户名已存在
            return 0;
        }
        //新增失败
        return -1;
    }

    //用户登录
    @Override
    public User loginUser(String username) {
        return userMapper.queryUser(username);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
