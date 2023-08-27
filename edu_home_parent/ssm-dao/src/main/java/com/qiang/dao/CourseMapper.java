package com.qiang.dao;

import com.qiang.domain.Course;
import com.qiang.domain.CourseVo;
import com.qiang.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /**
     * 多条件查询课程列表
     */
    public List<Course> findCourseByConditioin(CourseVo courseVo);

    /**
     * 保存课程信息
     */
    public Integer saveCourse(Course course);

    /**
     * 保存讲师信息
     */
    public void saveTeacher(Teacher teacher);
    /**
     * 修改课程信息
     */
    public Integer updateCourse(Course course);

    /**
     * 修改讲师信息
     */
    public void updateTeacher(Teacher teacher);

    public CourseVo findCourseById(int id);
    /**
     * 修改课程状态
     */
    public void updateCourseStatus(Course course);

}
