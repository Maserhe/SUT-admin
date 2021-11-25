package top.maserhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.entity.Homework;
import top.maserhe.mapper.HomeworkMapper;
import top.maserhe.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public List<HomeListVo> getAllList(Integer classId) {
        return homeworkMapper.getAllList(classId);
    }

    @Override
    public List<HomeListVo> getScoreList(Integer classId) {
        return homeworkMapper.getScoreList(classId);
    }
}
