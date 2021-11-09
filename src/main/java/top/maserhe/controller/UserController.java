package top.maserhe.controller;

import cn.hutool.core.bean.BeanUtil;
import com.csvreader.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.maserhe.common.dto.StuDto;
import top.maserhe.common.lang.Result;
import top.maserhe.common.vo.AdminVo;
import top.maserhe.common.vo.UserVo;
import top.maserhe.entity.User;
import top.maserhe.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {

        return userService.getById(id);
    }

    /**
     * 添加用户
     * @param stuDto
     * @return
     */
    @PostMapping("/addStu")
    public Result addStu(@RequestBody @Validated StuDto stuDto) {

        Assert.notNull(stuDto, "输入信息有误");
        if (BeanUtil.isEmpty(stuDto.getAvatar())) {
            stuDto.setAvatar("https://ghproxy.com/https://github.com/Maserhe/PIc-Bed/blob/master/typora/202110271336023.jpg");
        }
        User user = new User();
        BeanUtil.copyProperties(stuDto, user);
        // 插入数据库
        boolean save = userService.save(user);
        return Result.succ(save);
    }

    /**
     * 上传文件 导入用户
     * @return
     */
    @PostMapping("/upload")
    public Result uploadForAddStu(MultipartFile file, Integer id) {
        Assert.notNull(file, "上传文件不能为空");
        boolean flag = true;

        try {
            CsvReader csvReader = new CsvReader(file.getInputStream(), Charset.defaultCharset());
            // 1, 跳过首行
            csvReader.readHeaders();
            // 2, 读取数据
            while (csvReader.readRecord()) {
                String username = csvReader.get(0);
                String name = csvReader.get(1);
                String password = csvReader.get(2);

                Assert.notNull(username, "学号为空");
                Assert.notNull(name, "姓名为空");
                Assert.notNull(password, "密码为空");
                // 3, 数据写入
                User user = constructUser(username, name, password, id);
                boolean save = userService.save(user);
                if (! save) {
                    flag = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.succ(flag);
    }

    /**
     * 获取所有 学生
     * @param classId
     * @return
     */
    @GetMapping("/getAllStu")
    public Result getStuByClassId(Integer classId) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("class_id", classId);
        Collection<User> users = userService.listByMap(columnMap);

//        List<AdminVo> ans= new ArrayList<>(users.size());
//
//        users.forEach(t-> {
//            AdminVo adminVo = new AdminVo();
//            BeanUtil.copyProperties(t, adminVo);
//            ans.add(adminVo);
//        });

        return Result.succ(users);
    }

    @GetMapping("/delete")
    public Result deleteStu(Integer id) {

        boolean res = userService.removeById(id);
        return Result.succ(res);

    }

    /**
     * 快速构建 用户
     * @param username
     * @param name
     * @param password
     * @return
     */
    private User constructUser(String username, String name, String password, Integer id) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setType(2);
        user.setAvatar("https://ghproxy.com/https://github.com/Maserhe/PIc-Bed/blob/master/typora/202110271336023.jpg");
        user.setClassId(id);
        return user;
    }






}


