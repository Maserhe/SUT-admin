package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.FileDto;
import top.maserhe.common.dto.HomeWorkDto;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.*;
import top.maserhe.service.CourseService;
import top.maserhe.service.CourseTaskService;
import top.maserhe.service.HomeworkService;
import top.maserhe.service.ImgService;

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
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTaskService courseTaskService;

    @PostMapping("/upload")
    public Result uploadHomework(@Validated @RequestBody HomeWorkDto homeWorkDto) {
        Assert.notNull(homeWorkDto, "上传作业为空");
        // 重复上传应该更新上机报告
        final Map<String, Object> map = new HashMap<>(2);
        map.put("task_id", homeWorkDto.getTaskId());
        map.put("user_id", homeWorkDto.getUserId());
        // 1, 发现已经上传
        List<Homework> homeworks = homeworkService.listByMap(map).stream().collect(Collectors.toList());
        if (homeworks.size() > 0) {
            // 删除上一次上传的
            homeworkService.removeByMap(map);
        }
        Homework homework = new Homework();
        BeanUtil.copyProperties(homeWorkDto, homework);
        // 2, 上机报告插入数据库
        boolean save = homeworkService.save(homework);

        if (save) {
            final List<Homework> collect = homeworkService.listByMap(map).stream().collect(Collectors.toList());
            // 3, 上传 报告 和 运行截图
            List<String> imgs = homeWorkDto.getImgs();
            final List<Img> res = imgs.stream().map(t -> {
                Img img = new Img();
                img.setUrl(t);
                img.setHomeworkId(collect.get(0).getId());
                return img;
            }).collect(Collectors.toList());
            for (int i = 0; i < res.size(); i ++ ) {
                save = save && imgService.save(res.get(i));
            }
        }
        return Result.succ(save);
    }

    @GetMapping("/getHomeWork")
    public Result getHomeWork(@Validated @RequestBody FileDto fileDto) {
        Assert.notNull(fileDto, "参数错误");
        return Result.succ(getHomeWork2(fileDto));
    }

    @GetMapping("/getAll")
    public Result getAll(@RequestBody FileDto fileDto) {

        Assert.notNull(fileDto, "参数错误");
        // 1， 查询到报告
        Homework homeWork = getHomeWork2(fileDto);
        Assert.notNull(homeWork, "参数错误");
        // 2， 查询图片
        final Map<String, Object> map = new HashMap<>(1);
        map.put("homework_id", homeWork.getId());
        final List<Img> imgs = imgService.listByMap(map).stream().collect(Collectors.toList());
        // 3， 返回结果
        final Map<Object, Object> res = MapUtil.builder().put("homework", homeWork)
                .put("imgs", imgs).map();
        return Result.succ(res);
    }


    private Homework getHomeWork2(FileDto fileDto) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("user_id", fileDto.getUserId());
        map.put("task_id", fileDto.getTaskId());
        Homework homework = homeworkService.listByMap(map).stream().collect(Collectors.toList()).get(0);
        return homework;
    }

    /**
     * 获取 某个 班级可以查看的全部 作业列表。
     *
     * @param classId
     * @return
     */
    @GetMapping("/getList")
    public Result getAll(Integer classId) {

        // 1， 找到同班的课程
        Map<String, Object> map = new HashMap<>(1);
        map.put("class_id", classId);
        List<Course> courses = courseService.listByMap(map).stream().collect(Collectors.toList());
        // 2， 课程里面 的上机作业， 带有查看权限的
        final Map<String, Object> tMap = new HashMap<>(1);
        Map<String, Object> ans = new HashMap<>(courses.size());

        courses.stream().forEach(t-> {
            tMap.put("course_id", t.getId());
            List<CourseTask> courseTasks = courseTaskService.listByMap(tMap).stream().collect(Collectors.toList());
            ans.put(String.valueOf(t.getId()) , courseTasks);
        });
        // 3, 根据作业id 获取上机 上传的图片。

        


        return Result.succ(null);
    }





}
