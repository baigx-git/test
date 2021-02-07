package com.luxondata.test.controller;

import com.luxondata.test.entity.User;
import com.luxondata.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return userService.getUserList(user);
    }
}
