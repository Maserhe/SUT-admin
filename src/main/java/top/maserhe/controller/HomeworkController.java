package top.maserhe.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.FileDto;
import top.maserhe.common.dto.HomeWorkDto;
import top.maserhe.common.lang.Result;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.common.vo.HomeworkScoreVo;
import top.maserhe.common.vo.HomeworkTeacherVo;
import top.maserhe.common.vo.TaskGradeVo;
import top.maserhe.entity.*;
import top.maserhe.service.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private UserService userService;

    @Autowired
    private TaskGradeService taskGradeService;


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
     * 获取所有具有查看权限的 人
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/getByCourseIdList")
    public Result getList(Integer courseId, Integer userId) {
        List<HomeworkScoreVo> listByCourseIdAndUserId = homeworkService.getListByCourseIdAndUserId(courseId, userId);
        return Result.succ(construct(listByCourseIdAndUserId));
    }


    /**
     * 根据班级id 和 用户id 获取所有列表
     * @param classId
     * @param userId
     * @return
     */
    @PostMapping("/getByClassIdList")
    public Result getListByClassIdAndUserId(Integer classId, Integer userId) {
        List<HomeworkScoreVo> listByClassIdAndUserId = homeworkService.getListByClassIdAndUserId(classId, userId);
        return Result.succ(construct(listByClassIdAndUserId));
    }


    @PostMapping("/getByTeacherIdList")
    public Result getListByTeacherId(Integer teacherId) {
        List<HomeworkTeacherVo> list = homeworkService.getListByTeacherId(teacherId);
        constructTeacherVo(list);
        Collections.sort(list);
        return Result.succ(list);
    }


    /**
     * 对某个作业打分
     * @param homeworkId
     * @param score
     * @return
     */
    @PostMapping("/mark")
    public Result marking(Integer homeworkId, Integer score) {
        Homework homework = homeworkService.getById(homeworkId);
        homework.setFinalScore(score);
        final boolean res = homeworkService.updateById(homework);
        return Result.succ(res);
    }




    private List<HomeworkScoreVo> construct(List<HomeworkScoreVo> list) {
        // 1, 获取上机图片
        Map<String, Object> map = new HashMap<>(1);
        list.stream().forEach(t-> {
            t.setImgs(searchImgUrls(t.getId()));
            t.setAuthor(searchAuthor(t.getUserId()));
        });
        return list;
    }

    private List<HomeworkTeacherVo> constructTeacherVo(List<HomeworkTeacherVo> list) {
        final Map<String, Object> map = new HashMap<>(1);

        list.stream().forEach(t -> {

            List<TaskGrade> taskGrades = taskGradeService.listByMap(map).stream().collect(Collectors.toList());
            List<TaskGradeVo> taskGradeVos = taskGrades.stream().map(te -> {
                TaskGradeVo vo = new TaskGradeVo();
                BeanUtil.copyProperties(te, vo);
                vo.setAuthor(searchAuthor(te.getUserId()));
                return vo;
            }).collect(Collectors.toList());
            // 1,设置 vo
            t.setTaskGradeVoList(taskGradeVos);
            // 2, 设置 图片
            t.setImgs(searchImgUrls(t.getId()));
            // 3， 设置用户名
            t.setAuthor(searchAuthor(t.getUserId()));

        });

        return list;
    }

    /**
     * 查询 上机报告的图片
     * @param homeworkId
     * @return
     */
    private List<String> searchImgUrls(Integer homeworkId) {
        final Map<String, Object> map = new HashMap<>(1);
        map.put("homework_id", homeworkId);
        final List<Img> imgs = imgService.listByMap(map).stream().collect(Collectors.toList());
        final List<String> urls = imgs.stream().map(i -> {
            return i.getUrl();
        }).collect(Collectors.toList());
        return urls;
    }

    /**
     * 查询用户名
     * @param userId
     * @return
     */
    private String searchAuthor(Integer userId) {
        final Map<String, Object> map = new HashMap<>(1);
        map.put("id", userId);
        List<User> users = userService.listByMap(map).stream().collect(Collectors.toList());
        if (users.size() == 1) {
            return users.get(0).getName();
        }
        return null;
    }




}
