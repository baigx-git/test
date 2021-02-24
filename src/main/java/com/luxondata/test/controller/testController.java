package com.luxondata.test.controller;

import com.luxondata.test.component.exception.TestException;
import com.luxondata.test.entity.User;
import com.luxondata.test.service.UserService;
import com.luxondata.test.util.SecurityContextHolderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试系统")
public class testController {

    @Autowired
    private UserService userService;

    @ApiImplicitParam(name = "s", value = "传参", required = true, dataType = "String")
    @ApiOperation(value = "测试接口是否通")
    @GetMapping("/demo")
    public String get(String s) {
        return "111";
    }

    @ApiOperation(value = "查询用户", nickname = "添加用户接口1", notes = "入参是复杂对象", produces = "application/json")
    @PostMapping("/getUsers")
    @ResponseBody
    public List<User> getUserList(@RequestBody User user) {
        Integer a = 1 / 0;
        return userService.getUserList(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "登录并返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
    })
    public Object login(String username, String password, HttpServletResponse response) {

        String token = userService.login(username, password);
        response.addHeader("Authorization", token);
        return token;
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    @ApiImplicitParam(name = "user", value = "用户对象", dataType = "User", paramType = "body")
    public Object register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @GetMapping("/getCurrentUser")
    @ApiOperation(value = "获取当前登录用户信息")
    public Object getCurrentUser() {
        return SecurityContextHolderUtil.getCurrentUser();
    }

}
