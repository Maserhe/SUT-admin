package top.maserhe.controller;


import cn.hutool.core.map.MapUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.StuClassDTO;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.Course;
import top.maserhe.entity.CourseTask;
import top.maserhe.entity.Homework;
import top.maserhe.entity.StuClass;
import top.maserhe.service.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 班级控制
 *
 * @author Maserhe
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/stu-class")
public class StuClassController {

    @Autowired
    private StuClassService stuClassService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTaskService courseTaskService;

    @Autowired
    private HomeworkService homeworkService;


    /**
     * 获取所有 班级
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/getAll")
    public Result getAllClass() {
        List<StuClass> list = stuClassService.list();
        return Result.succ(list);
    }

    /**
     * 获取所有格式化的 班级序列
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/getAllFormatClass")
    public Result getAllFormatClass() {
        List<StuClass> list = stuClassService.list();
        List<Map<Object, Object>> ans = new ArrayList<>(list.size());
        list.forEach(t-> {
            Map<Object, Object> build = MapUtil.builder().put("id", t.getId()).put("value", t.getGrade() + "级" + t.getMajor()).build();
            ans.add(build);
        });
        return Result.succ(ans);
    }



    /**
     * 删除班级
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/delete")
    public Result deleteClass(int id) {

        boolean flag = stuClassService.removeById(id);

        // 1， 需要把该班级的学生也全部删除
        final Map<String, Object> map = new HashMap<>(1);
        map.put("class_id", id);
        userService.removeByMap(map);
        // 2, 把课程也给删除
        Collection<Course> courses = courseService.listByMap(map);
        courses.stream().forEach(t-> {
            courseService.deleteCourse(t.getId());
        });

        return Result.succ(flag);
    }

    /**
     * 添加班级
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/addClass")
    public Result addClass(@Validated @RequestBody StuClassDTO stuClassDTO) {

        StuClass stuClass = new StuClass();
        BeanUtils.copyProperties(stuClassDTO, stuClass);
        boolean save = stuClassService.save(stuClass);
        return Result.succ(save);
    }
}
