package com.ddbes.dailytask.dao;

import com.ddbes.dailytask.entity.Provider;
import com.ddbes.dailytask.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    @SelectProvider(type = Provider.class,method = "findTask")
    List<Map<String,Object>> findTask(Map param);

//    @Select("SELECT g.groupId,g.groupName,COUNT(u.id) count FROM `group` g LEFT JOIN `user` u ON u.groupId = g.groupId GROUP BY g.groupId")
//    List<Map<String,Object>> findGroup();
//
//    @Results({
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "username",property = "username"),
//            @Result(column = "id",property = "taskList",javaType = List.class,many = @Many(select = "taskList"))
//    })
//    @Select("SELECT id,username FROM `user` WHERE groupId = #{groupId}")
//    List<Map<String,Object>> findMember(String group);
//
//    @Select("SELECT content,complete,plan FROM task WHERE userId = #{id} ORDER BY datetime DESC")
//    Map taskList(String id);

    @Select("SELECT a.date,t.user_id,t.datetime,IFNULL(t.content,'休假') content,t.complete,t.plan FROM (SELECT DATE_FORMAT(date, '%Y-%m-%d') date FROM calendar WHERE date >= (SELECT date_sub(curdate(),INTERVAL WEEKDAY(curdate()) DAY)) AND date <= (SELECT date_sub(curdate(),INTERVAL WEEKDAY(curdate()) - 4 DAY))) a LEFT JOIN (SELECT user_id,datetime,content,complete,plan from task WHERE user_id = 1) t ON DATE_FORMAT(t.datetime, '%Y-%m-%d') = a.date ORDER BY a.date ASC")
    List<Map<String,Object>> findTaskByUserId(@Param("userId") Long userId);
}
