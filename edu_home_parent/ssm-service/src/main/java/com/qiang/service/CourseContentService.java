package com.qiang.service;

import com.qiang.domain.Course;
import com.qiang.domain.CourseLesson;
import com.qiang.domain.CourseSection;

import java.util.List;

/**
 * 根据课程ID查询章节与课时信息
 */
public interface CourseContentService {
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节信息
     */
    public void saveSection(CourseSection section);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * @param id
     * @param status
     */
    public void updateSectionStatus(int id,int status);

    /**
     * 保存课时信息
     */
    public void saveLesson(CourseLesson lesson);
    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson lesson);

}
