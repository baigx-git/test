package com.luxondata.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luxondata.test.dao.UserMapper;
import com.luxondata.test.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends  UserDetailsService {

  public  List<User> getUserList(User user);

  public String login(String username, String password);

  boolean register(User userToAdd);

}
