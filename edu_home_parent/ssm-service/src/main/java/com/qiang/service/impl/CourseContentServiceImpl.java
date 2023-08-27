package com.qiang.service.impl;

import com.qiang.dao.CourseContentMapper;
import com.qiang.domain.Course;
import com.qiang.domain.CourseLesson;
import com.qiang.domain.CourseSection;
import com.qiang.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return list;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course courseByCourseId = courseContentMapper.findCourseByCourseId(courseId);
        return courseByCourseId;
    }

    @Override
    public void saveSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        courseContentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {

        //补全信息
        section.setUpdateTime(new Date());
        courseContentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        //补全信息
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        courseContentMapper.saveLesson(lesson);
    }

    @Override
    public void updateLesson(CourseLesson lesson) {
        //补全信息
        lesson.setUpdateTime(new Date());
        courseContentMapper.updateLesson(lesson);
    }
}
