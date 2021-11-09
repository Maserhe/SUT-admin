package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.CourseDto;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.Course;
import top.maserhe.service.CourseService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 通过 classId 获取所有课程
     * @param classId
     * @return
     */
    @GetMapping("/getAll")
    public Result getCourse(Integer classId) {
        Assert.notNull(classId, "班级号为空");
        Map<String, Object> map = new HashMap<>(1);
        map.put("class_id", classId);
        final Collection<Course> courses = courseService.listByMap(map);
        return Result.succ(courses);
    }

    /**
     * 添加课程
     * @param courseDto
     * @return
     */
    @PostMapping("/add")
    public Result addCourse(@RequestBody @Validated CourseDto courseDto) {

        Course course = new Course();
        BeanUtil.copyProperties(courseDto, course);
        boolean save = courseService.save(course);
        return Result.succ(save);
    }

    @PostMapping("/delete")
    public Result deleteCourse(Integer courseId) {
        final boolean res = courseService.removeById(courseId);
        return Result.succ(res);
    }


}
