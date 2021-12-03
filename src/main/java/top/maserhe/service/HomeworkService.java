package top.maserhe.service;

import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.common.vo.HomeworkScoreVo;
import top.maserhe.common.vo.HomeworkTeacherVo;
import top.maserhe.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;
import top.maserhe.mapper.HomeworkMapper;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
public interface HomeworkService extends IService<Homework> {




    /**
     * 获取某个人在某一个课程下面所有的 可以打分的作业列表
     * @param courseId
     * @param userId
     * @return
     */
    public List<HomeworkScoreVo> getListByCourseIdAndUserId(Integer courseId, Integer userId);


    /**
     * 根据班级id 获取所有
     * @param classId
     * @param userId
     * @return
     */
    public List<HomeworkScoreVo> getListByClassIdAndUserId(Integer classId, Integer userId);


    /**
     * 根据teacherid 查询所有可以 更改的作业。
     * @param teacherId
     * @return
     */
    public List<HomeworkTeacherVo> getListByTeacherId(Integer teacherId);

    /**
     * 删除文件
     * @param id
     * @return
     */
    public boolean deleteHomeworkFile(Integer id);

}
