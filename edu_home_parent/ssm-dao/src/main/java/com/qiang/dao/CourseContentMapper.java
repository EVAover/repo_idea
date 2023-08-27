package com.qiang.dao;

import com.qiang.domain.Course;
import com.qiang.domain.CourseLesson;
import com.qiang.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节信息
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /**
     *修改章节状态
     */
    public void updateSectionStatus(CourseSection section);

    /**
     * 保存课时信息
     */
    public void saveLesson(CourseLesson lesson);
    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson lesson);
}
