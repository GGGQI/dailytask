package com.ddbes.dailytask.controller;

import com.ddbes.dailytask.entity.Task;
import com.ddbes.dailytask.kit.Config;
import com.ddbes.dailytask.kit.DateKit;
import com.ddbes.dailytask.kit.StrKit;
import com.ddbes.dailytask.service.ITaskService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @PostMapping("/addTask")
    @ApiOperation("添加任务")
    public ResponseEntity addTask(@RequestBody @Valid Task task) {
        if (StrKit.isBlank(task.getDatetime()+"")) {
        task.setDatetime(new Date());
        }
        task.setId(Config.idWorker.nextId());
        return taskService.addTask(task);
    }

    @PutMapping("/updateTask")
    @ApiOperation("修改任务")
    public ResponseEntity updateTask(@RequestBody @Valid UpdateTask updateTask) {
        Task task = new Task();
        BeanUtils.copyProperties(updateTask,task);
        return taskService.updateTask(task);
    }

    @GetMapping("/findTask")
    @ApiOperation("条件查询任务")
    public ResponseEntity findTask(@RequestParam(value = "userId" ,required = false)String userId,@RequestParam(value = "groupId" ,required = false)String groupId,@RequestParam(value = "startTime" ,required = false)String startTime,@RequestParam(value = "endTime" ,required = false)String endTime ) {
        if (StrKit.notBlank(startTime) && StrKit.notBlank(endTime)) {
            Date start = DateKit.DTStringtoDate(startTime, "yyyy-MM-dd");
            Date end = DateKit.DTStringtoDate(endTime, "yyyy-MM-dd");
            if(start.compareTo(end) == 1) {
                Map map = new HashMap();
                map.put("msg","请输入正确的时间段");
                return new ResponseEntity(map, HttpStatus.OK);
            }
        }
        Map param = new HashMap();
        param.put("userId",userId == null? "":userId);
        param.put("groupId",groupId == null? "":groupId);
        param.put("startTime",startTime == null ? "":startTime);
        param.put("endTime",endTime == null ? "":endTime);
        return taskService.findTask(param);
    }

    @GetMapping("findTask/{userId}")
    @ApiOperation("查询个人本周任务")
    public ResponseEntity findTask(@PathVariable("userId")Long userId) {
        return taskService.findTaskByUserId(userId);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    @ApiOperation("删除任务")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId){
        return taskService.deleteTask(taskId);
    }

    @Data
    @ApiModel("修改任务")
    @NoArgsConstructor
    public static class UpdateTask implements Serializable {
        @ApiModelProperty("任务id")
        @NonNull
        private Long id;
        @ApiModelProperty("任务时间")
        private Date datetime;
        @ApiModelProperty("工作内容")
        private String content;
        @ApiModelProperty("完成情况")
        private String complete;
        @ApiModelProperty("计划外任务")
        private String plan;
        @ApiModelProperty("星期")
        private int sort;
    }
    //报表打印
//    @PostMapping("/print")
//    @ApiOperation("报表打印")
//    public Object print(@RequestBody Map param) throws IOException {
//        if (param.get("userId") == null) {
//            param.put("userId",null);
//        }
//        return taskService.print(param.get("userId")+"",param.get("startTime")+"",param.get("endTime")+"");
//    }

//    @Data
//    @ApiModel(value = "打印任务")
//    public class PrintTask implements Serializable {
//        @ApiModelProperty("用户id")
//        private String userId;
//        @ApiModelProperty("组id")
//        private String groupId;
//        @ApiModelProperty("开始时间")
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        @NotNull
//        private Date startTime;
//        @ApiModelProperty("结束时间")
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        @NotNull
//        private Date endTime;
//    }

}
