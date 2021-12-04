package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.common.vo.HomeworkScoreVo;
import top.maserhe.common.vo.HomeworkTeacherVo;
import top.maserhe.entity.Homework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {




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
     * 根据 作业id 和 学生id 获取所有
     * @param taskId
     * @param userId
     * @return
     */
    public List<HomeworkScoreVo> getListByTaskIdAndUserId(Integer taskId, Integer userId);


    /**
     * 根据teacherid 查询所有可以 更改的作业。
     * @param teacherId
     * @return
     */
    public List<HomeworkTeacherVo> getListByTeacherId(Integer teacherId);

}
