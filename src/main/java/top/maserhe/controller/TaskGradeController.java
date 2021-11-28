package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.maserhe.common.dto.TaskGradeDto;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.StuClass;
import top.maserhe.entity.TaskGrade;
import top.maserhe.service.TaskGradeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  对作业 打分管理
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/task-grade")
public class TaskGradeController {

    @Autowired
    private TaskGradeService taskGradeService;


    /**
     * 对某个作业打分
     * @param taskGradeDto
     * @return
     */
    @PostMapping("/mark")
    public Result marking(@RequestBody TaskGradeDto taskGradeDto) {
        TaskGrade taskGrade = new TaskGrade();
        BeanUtil.copyProperties(taskGradeDto, taskGrade);
        return Result.succ(taskGradeService.save(taskGrade));
    }

    /**
     * 不包含分数 是否打过分
     * @param taskGradeDto
     * @return
     */
    @PostMapping("/isMark")
    public Result isMark(@RequestBody TaskGradeDto taskGradeDto) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("homework_id", taskGradeDto.getHomeworkId());
        map.put("user_id", taskGradeDto.getUserId());
        Collection<TaskGrade> taskGrades = taskGradeService.listByMap(map);
        return Result.succ( taskGrades.size() == 1);
    }

    /**
     * 修改分数
     * @param
     * @return
     */
    @PostMapping("/updateScore")
    public Result update(@RequestBody TaskGradeDto taskGradeDto) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("homework_id", taskGradeDto.getHomeworkId());
        map.put("user_id", taskGradeDto.getUserId());

        List<TaskGrade> taskGrades = taskGradeService.listByMap(map).stream().collect(Collectors.toList());
        boolean res = false;
        if (taskGrades.size() == 1) {
            TaskGrade taskGrade = taskGrades.get(0);
            taskGrade.setScore(taskGradeDto.getScore());
            res = taskGradeService.updateById(taskGrade);
        }

        return Result.succ(res);
    }


}
