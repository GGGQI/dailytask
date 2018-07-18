package com.ddbes.dailytask.entity;

import com.ddbes.dailytask.kit.StrKit;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class Provider {

    public String findTask(Map param) {
        SQL sql = new SQL().SELECT("t.id,u.username,t.user_id,DATE_FORMAT(t.datetime,'%Y-%m-%d') datetime,t.content,t.complete,t.plan").FROM("task t LEFT JOIN USER u ON u.id = t.user_id LEFT JOIN t_group g ON u.group_id = g.groupId");
        if (StrKit.notBlank(param.get("userId")+"")) {
            sql.WHERE("u.Id = #{userId}");
        }
        if (StrKit.notBlank(param.get("groupId")+"")) {
            sql.WHERE("g.groupId = #{groupId}");
        }
        if (StrKit.notBlank(param.get("startTime")+"")) {
            sql.WHERE("t.datetime >= #{startTime}");
        }
        if (StrKit.notBlank(param.get("endTime")+"")) {
            sql.WHERE("t.datetime <= #{endTime}");
        }
        return sql.toString()+" order by t.datetime desc";
    }

}
