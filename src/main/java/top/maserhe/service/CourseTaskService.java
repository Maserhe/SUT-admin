package top.maserhe.service;

import top.maserhe.entity.CourseTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
public interface CourseTaskService extends IService<CourseTask> {


    /**
     * 删除上机任务
     * @param id
     * @return
     */
    public boolean deleteTask(Integer id);
}
