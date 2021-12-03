package top.maserhe.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.entity.CourseTask;
import top.maserhe.entity.Homework;
import top.maserhe.mapper.CourseTaskMapper;
import top.maserhe.service.CourseTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.maserhe.service.HomeworkService;

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
public class CourseTaskServiceImpl extends ServiceImpl<CourseTaskMapper, CourseTask> implements CourseTaskService {

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private CourseTaskMapper courseTaskMapper;

    public boolean deleteTask(Integer id){
        Map<String, Object> map = new HashMap<>(1);
        map.put("task_id", id);
        Collection<Homework> homework = homeworkService.listByMap(map);
        homework.forEach(t-> {
            homeworkService.deleteHomeworkFile(t.getId());
        });
        // 删除 本条记录
        return courseTaskMapper.deleteById(id) == 1? true: false;
    }
}
