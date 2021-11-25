package top.maserhe.controller;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.maserhe.common.lang.Result;
import top.maserhe.common.vo.HomeListVo;
import top.maserhe.mapper.HomeworkMapper;

import java.util.List;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/10/28 6:26 下午
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello !";
    }

    @Autowired
    private HomeworkMapper homeworkMapper;

    @RequestMapping("/getAll")
    private Result getList(Integer classId) {
        List<HomeListVo> allList = homeworkMapper.getAllList(classId);
        return Result.succ(allList);
    }
}
