package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.CourseDto;
import top.maserhe.common.lang.Result;
import top.maserhe.common.vo.CourseResourceVo;
import top.maserhe.common.vo.CourseVo;
import top.maserhe.entity.Course;
import top.maserhe.entity.StuClass;
import top.maserhe.service.CourseService;
import top.maserhe.service.StuClassService;
import top.maserhe.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Autowired
    private StuClassService stuClassService;

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

        final List<Course> courses = courseService.listByMap(map).stream().collect(Collectors.toList());
        final List<CourseVo> ans = courses.stream().map(t -> {
            CourseVo temp = new CourseVo();
            BeanUtil.copyProperties(t, temp);
            temp.setTeacherName(userService.getById(t.getTeacherId()).getName());
            return temp;
        }).collect(Collectors.toList());
        return Result.succ(ans);
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

    /**
     *  根据老师id 获取所有课程
     * @param teacherId
     * @return
     */
    @GetMapping("/getCourses")
    public Result getCourses(Integer teacherId) {

        Map<String, Object> map = new HashMap<>(1);
        map.put("teacher_id", teacherId);

        // 查询出来所有的课程
        List<Course> res = courseService.listByMap(map).stream().collect(Collectors.toList());

        // 根据classId 获取年级
        final List<CourseResourceVo> vos = res.stream().map(t -> {

            CourseResourceVo vo = new CourseResourceVo();
            BeanUtil.copyProperties(t, vo);

            // 查询出来 年级 和 专业
            StuClass stuClass = stuClassService.getById(t.getClassId());
            vo.setMajor(stuClass.getMajor());
            vo.setGrade(stuClass.getGrade());

            return vo;
        }).collect(Collectors.toList());
        Collections.sort(vos);
        return Result.succ(vos);
    }




}
