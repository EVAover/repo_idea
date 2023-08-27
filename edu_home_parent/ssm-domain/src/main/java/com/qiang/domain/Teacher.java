package com.qiang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 教师类
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    //id
    private Integer id;

    //课程id
    private int courseId;

    //讲师姓名
    private String teacherName;

    //讲师职务
    private String position;

    //介绍
    private String description;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //是否删除
    private int isDel;
}
