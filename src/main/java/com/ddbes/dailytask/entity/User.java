package com.ddbes.dailytask.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Data
public class User {
    @Id
    @ApiModelProperty(value = "用户Id",hidden = true)
    private Long id;
    @ApiModelProperty("用户名")
    @NotNull
    private String username;
    @ApiModelProperty("所属组Id")
    @NotNull
    private Long groupId;
    @ApiModelProperty("登录名")
    @NotNull
    private String loginName;
    @ApiModelProperty("密码")
    @NotNull
    private String password;
}
