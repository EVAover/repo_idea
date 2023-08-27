package com.qiang.service;

import com.qiang.domain.Course;
import com.qiang.domain.CourseVo;

import java.util.List;

public interface CourseService {
    /**
     * 多条件查询课程列表
     */
    public List<Course> findCourseByConditioin(CourseVo courseVo);

    /**
     * 保存课程信息
     */
    public void saveCourseOrTeacher(CourseVo courseVo);

    /**
     * 修改课程及讲师信息
     */
    public void updateCourseOrTeacher(CourseVo courseVo);

    /**
     * 查询（回显）课程信息
     */
    public CourseVo findCourseById(int id);
    /**
     * 修改课程状态
     */
    public void updateCourseStatus(int id,int status);
}
