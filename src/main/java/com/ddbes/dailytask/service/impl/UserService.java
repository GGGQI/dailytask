package com.ddbes.dailytask.service.impl;

import com.ddbes.dailytask.dao.UserMapper;
import com.ddbes.dailytask.entity.User;
import com.ddbes.dailytask.service.IUserService;
import com.ddbes.dailytask.kit.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public ResponseEntity creatUser(User user) {
        HashMap map = new HashMap();
        int i = userMapper.insert(user);
        if (i != 0) {
            map.put("msg","success");
        }else {
            map.put("msg","服务器异常");
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public ResponseEntity updateUser(User user) {
        Map map = new HashMap();
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i != 0) {
            map.put("msg","success");
        }else {
            map.put("msg","用户不存在");
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity deleteUser(Long userId) {
        Map map = new HashMap();
        User user = new User();
        user.setId(userId);
        int i = userMapper.deleteByPrimaryKey(userId);
        if (i != 0) {
            map.put("msg","success");
        }else {
            map.put("msg","服务器异常");
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public ResponseEntity login(String loginName, String password) {
        Map map = new HashMap();
        Map result = userMapper.login(loginName,password);
        if (StrKit.isBlank(result.get("id")+"")) {
            map.put("msg","用户或密码不正确");
        } else {
            map.put("msg","success");
            map.put("userInfo",result);
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }
}
