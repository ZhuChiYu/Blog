package com.zcy.blog.service;

import com.zcy.blog.po.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkUser(String username,String password);
}
