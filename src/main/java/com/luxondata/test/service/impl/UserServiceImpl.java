package com.luxondata.test.service.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luxondata.test.component.jwt.JwtTokenUtil;
import com.luxondata.test.dao.UserMapper;
import com.luxondata.test.entity.User;
import com.luxondata.test.service.UserService;
import com.luxondata.test.util.Builder;
import com.luxondata.test.vo.CUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<User> getUserList(User user) {
        return userMapper.selectList(Wrappers.<User>lambdaQuery().eq(StringUtils.isNotBlank(user.getUsername()), User::getUsername, user.getUsername()));
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;

    }

    @Override
    public boolean register(User user) {
        final String username = user.getUsername();
        if (Objects.nonNull(userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)))) {
            return false;
        }
        user.setId(IdWorker.getIdStr());
        user.setUsername(username);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        CUserDetails cUserDetails = new CUserDetails();
        cUserDetails.setId(user.getId());
        cUserDetails.setUsername(user.getUsername());
        cUserDetails.setPassword(user.getPassword());
        cUserDetails.setAuthorityList(Collections.EMPTY_LIST);
        cUserDetails.setRole("ROLE_ADMIN");
//        Builder<CUserDetails> entiy = Builder.of(CUserDetails::new).with(
//                CUserDetails::setAuthorityList, Collections.EMPTY_LIST).with(
//                CUserDetails::setRole, "ROLE_ADMIN"
//        );
        return cUserDetails;
    }
}
