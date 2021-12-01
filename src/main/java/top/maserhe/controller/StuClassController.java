package top.maserhe.controller;


import cn.hutool.core.map.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.maserhe.common.dto.StuClassDTO;
import top.maserhe.common.lang.Result;
import top.maserhe.entity.StuClass;
import top.maserhe.service.StuClassService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级控制
 *
 * @author Maserhe
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/stu-class")
public class StuClassController {

    @Autowired
    private StuClassService stuClassService;

    /**
     * 获取所有 班级
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllClass() {
        List<StuClass> list = stuClassService.list();
        return Result.succ(list);
    }

    /**
     * 获取所有格式化的 班级序列
     * @return
     */
    @GetMapping("/getAllFormatClass")
    public Result getAllFormatClass() {
        List<StuClass> list = stuClassService.list();
        List<Map<Object, Object>> ans = new ArrayList<>(list.size());
        list.forEach(t-> {
            Map<Object, Object> build = MapUtil.builder().put("id", t.getId()).put("value", t.getGrade() + "级" + t.getMajor()).build();
            ans.add(build);
        });
        return Result.succ(ans);
    }



    /**
     * 删除班级
     * @return
     */
    @PostMapping("/delete")
    public Result deleteClass(int id) {
        boolean flag = stuClassService.removeById(id);
        return Result.succ(flag);
    }

    /**
     * 添加班级
     * @return
     */
    @PostMapping("/addClass")
    public Result addClass(@Validated @RequestBody StuClassDTO stuClassDTO) {

        StuClass stuClass = new StuClass();
        BeanUtils.copyProperties(stuClassDTO, stuClass);
        boolean save = stuClassService.save(stuClass);
        return Result.succ(save);
    }
}
