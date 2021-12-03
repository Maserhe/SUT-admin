package top.maserhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.common.vo.HomeworkScoreVo;
import top.maserhe.common.vo.HomeworkTeacherVo;
import top.maserhe.entity.Homework;
import top.maserhe.entity.Img;
import top.maserhe.mapper.HomeworkMapper;
import top.maserhe.service.FileStorageService;
import top.maserhe.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.maserhe.service.ImgService;
import top.maserhe.service.TaskGradeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private TaskGradeService taskGradeService;

    @Override
    public List<HomeworkScoreVo> getListByCourseIdAndUserId(Integer courseId, Integer userId) {
        return homeworkMapper.getListByCourseIdAndUserId(courseId, userId);
    }

    @Override
    public List<HomeworkScoreVo> getListByClassIdAndUserId(Integer classId, Integer userId) {
        return homeworkMapper.getListByClassIdAndUserId(classId, userId);
    }

    @Override
    public List<HomeworkTeacherVo> getListByTeacherId(Integer teacherId) {
        return homeworkMapper.getListByTeacherId(teacherId);
    }

    /**
     * 删除文件
     * @param id
     * @return
     */
    @Override
    public boolean deleteHomeworkFile(Integer id){
        final Homework homework = homeworkMapper.selectById(id);

        boolean flag = true;

        if (homework != null) {
            // 1, 查询所有
            String fileName = homework.getTaskFile();
            if (fileName != null) {
                fileStorageService.deleteFile(fileName);
            }
            // 2, 删除 上机图片
            Map<String, Object> map = new HashMap<>(1);
            map.put("homework_id", id);
            Collection<Img> imgs = imgService.listByMap(map);
            imgs.forEach(t-> {
                fileStorageService.deleteFile(t.getUrl());
            });
            flag = flag && imgService.removeByMap(map);
            // 3, 删除打分
            flag = flag && taskGradeService.removeByMap(map);
        }
        // 删除本条记录
        homeworkMapper.deleteById(id);
        return flag;
    }
}
