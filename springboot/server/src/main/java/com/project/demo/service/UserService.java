package com.project.demo.service;

import com.project.demo.dao.UserMapper;
import com.project.demo.entity.User;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

/**
 * 用户账户：用于保存用户登录信息(User)表服务接口
 *
 */
@Service
public class UserService extends BaseService<User> {

}


