package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.CourseTaskDto;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.CourseTask;
import top.maserhe.service.CourseTaskService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/course-task")
public class CourseTaskController {

    @Autowired
    private CourseTaskService courseTaskService;


    /**
     * 获取某个课程 的所有上机任务
     * @param courseId
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllTask(Integer courseId) {

        final Map<String, Object> map = new HashMap<>(1);
        map.put("course_id", courseId);
        Collection<CourseTask> ans = courseTaskService.listByMap(map);
        return Result.succ(ans);
    }


    /**
     *  添加 课程表
     * @param courseTaskDto
     * @return
     */
    @PostMapping("/addTask")
    public Result addTask(@Validated @RequestBody CourseTaskDto courseTaskDto) {
        Assert.notNull(courseTaskDto, "参数有问题");

        CourseTask courseTask = new CourseTask();
        BeanUtil.copyProperties(courseTaskDto, courseTask);
        // 插入数据库中
        boolean save = courseTaskService.save(courseTask);
        return Result.succ(save);
    }

    /**
     * 删除上机作业
     * @param taskId
     * @return
     */
    @PostMapping("/deleteTask")
    public Result deleteTask(Integer taskId) {
        Assert.notNull(taskId, "作业id不能为空d");
        final boolean res = courseTaskService.removeById(taskId);
        return Result.succ(res);
    }

    /**
     * 更新 任务
     * @param courseTask
     * @return
     */
    @PostMapping("/updateTask")
    public Result updateTask(@Validated @RequestBody CourseTask courseTask) {
        Assert.notNull(courseTask, "参数为空");
        final boolean res = courseTaskService.updateById(courseTask);
        return Result.succ(res);
    }

}
