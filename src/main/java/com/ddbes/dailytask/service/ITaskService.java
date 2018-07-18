package com.ddbes.dailytask.service;

import com.ddbes.dailytask.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITaskService {
    ResponseEntity addTask(Task task);

    ResponseEntity findTask(Map param);

    ResponseEntity findTaskByUserId(Long userId);

    ResponseEntity updateTask(Task task);

    ResponseEntity deleteTask(Long taskId);

//    Object print(String userId, String startTime, String endTime) throws IOException;
}
