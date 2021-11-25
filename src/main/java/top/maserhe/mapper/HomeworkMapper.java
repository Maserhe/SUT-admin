package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.common.vo.HomeListVo;
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
     * 获取 所有有查看权限的 作业列表
     * @param classId
     * @return
     */
    public List<HomeListVo> getAllList(Integer classId);


    /**
     * 获取 可以打分权限的列表
     * @param classId
     * @return
     */
    public List<HomeListVo> getScoreList(Integer classId);
}
