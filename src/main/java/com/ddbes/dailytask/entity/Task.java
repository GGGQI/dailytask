package com.ddbes.dailytask.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
public class Task {
    @Id
    @ApiModelProperty(value = "任务id",hidden = true)
    private Long id;
    @ApiModelProperty("用户id")
    @NonNull
    private Long userId;
    @ApiModelProperty("任务时间")
    private Date datetime;
    @ApiModelProperty("工作内容")
    @NonNull
    private String content;
    @ApiModelProperty("完成情况")
    private String complete;
    @ApiModelProperty("计划外任务")
    private String plan;

}
