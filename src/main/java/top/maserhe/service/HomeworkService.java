package top.maserhe.service;

import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.common.vo.HomeListVo;
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
     * 获取 所有 有查看权限的 作业列表
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
