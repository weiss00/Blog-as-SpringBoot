package com.weiss.blog.service.impl;

import com.weiss.blog.dao.UserRepository;
import com.weiss.blog.entity.User;
import com.weiss.blog.service.UserService;
import com.weiss.blog.util.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }


}
