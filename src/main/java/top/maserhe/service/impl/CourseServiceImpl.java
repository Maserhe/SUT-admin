package top.maserhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.entity.Course;
import top.maserhe.entity.CourseTask;
import top.maserhe.entity.Homework;
import top.maserhe.mapper.CourseMapper;
import top.maserhe.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.maserhe.service.CourseTaskService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseTaskService courseTaskService;


    /**
     * 删除课程
     * @param id
     * @return
     */
    @Override
    public boolean deleteCourse(Integer id) {

        Map<String, Object> map = new HashMap<>(1);
        map.put("course_id", id);
        Collection<CourseTask> courseTasks = courseTaskService.listByMap(map);
        courseTasks.forEach(t-> {
            courseTaskService.deleteTask(t.getId());
        });
        // 删除 本条记录
        return courseMapper.deleteById(id) == 1? true: false;

    }
}
