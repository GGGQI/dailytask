package com.ddbes.dailytask.controller;

import com.ddbes.dailytask.entity.User;
import com.ddbes.dailytask.kit.Config;
import com.ddbes.dailytask.service.IUserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseEntity login(@RequestBody @Valid Login login) {
        return userService.login(login.getLoginName(),login.getPassword());
    }

    @PostMapping("/createUser")
    @ApiOperation("添加用户")
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        user.setId(Config.idWorker.nextId());
        return userService.creatUser(user);
    }

    @PutMapping("/updateUser")
    @ApiOperation("修改用户")
    public ResponseEntity updateUser(@RequestBody @Valid UpdateUser updateUser) {
        User user = new User();
        BeanUtils.copyProperties(updateUser,user);
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    @ApiOperation("删除用户")
    public ResponseEntity deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    @Data
    @ApiModel("用户登录")
    public static class Login implements Serializable {
        @ApiModelProperty("登录名")
        @NotNull
        private String loginName;
        @ApiModelProperty("密码")
        @NotNull
        private String password;
    }

    @Data
    @ApiModel("用户修改信息")
    public static class UpdateUser implements Serializable {
        @ApiModelProperty("用户Id")
        @NotNull
        private Long id;
        @ApiModelProperty("用户名")
        private String username;
        @ApiModelProperty("所属组Id")
        private Long groupId;
        @ApiModelProperty("登录名")
        private String loginName;
        @ApiModelProperty("密码")
        private String password;
    }

}
