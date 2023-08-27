package com.qiang.service.impl;

import com.qiang.dao.CourseMapper;
import com.qiang.domain.Course;
import com.qiang.domain.CourseVo;
import com.qiang.domain.Teacher;
import com.qiang.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByConditioin(CourseVo courseVo) {
        List<Course> courseList = courseMapper.findCourseByConditioin(courseVo);

        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVo);
            //补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            //保存课程
            courseMapper.saveCourse(course);

            //获取新插入数据的couurseID
            int id = course.getId();
            //封装教师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);
            //补全
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            //保存讲师信息
            courseMapper.saveTeacher(teacher);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVo);
            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);
            //保存课程
            courseMapper.updateCourse(course);

            //封装教师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);
            //补全
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);
            //保存讲师信息
            courseMapper.updateTeacher(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public CourseVo findCourseById(int id) {
        CourseVo courseById = courseMapper.findCourseById(id);

        return courseById;
    }

    @Override
    public void updateCourseStatus(int id,int status) {
        try {
            Course course = new Course();
            //补全信息
            course.setUpdateTime(new Date());
            course.setId(id);
            course.setStatus(status);
            courseMapper.updateCourseStatus(course);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
