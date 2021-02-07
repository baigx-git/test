package com.luxondata.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luxondata.test.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
