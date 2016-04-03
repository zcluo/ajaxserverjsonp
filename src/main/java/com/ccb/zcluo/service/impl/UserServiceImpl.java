package com.ccb.zcluo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ccb.zcluo.IDao.UserMapper;
import com.ccb.zcluo.User;
import com.ccb.zcluo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserById(int id){
        return this.userMapper.selectByPrimaryKey(id);
    }
}
