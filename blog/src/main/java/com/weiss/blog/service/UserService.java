package com.weiss.blog.service;

import com.weiss.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);

}
