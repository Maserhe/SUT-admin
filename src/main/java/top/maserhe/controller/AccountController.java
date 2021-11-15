package top.maserhe.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.maserhe.common.dto.LoginDto;
import top.maserhe.common.lang.Result;
import top.maserhe.common.vo.AdminVo;
import top.maserhe.common.vo.UserVo;
import top.maserhe.entity.StuClass;
import top.maserhe.entity.User;
import top.maserhe.service.StuClassService;
import top.maserhe.service.UserService;
import top.maserhe.util.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/1 2:16 下午
 **/
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    StuClassService stuClassService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 登陆
     * @param loginDto
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(loginDto.getPassword())){
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        // 根据 用户id 来
        return getVo(user);
    }

    /**
     * 退出登陆
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(200, "成功退出登陆", null);
    }

    /**
     * 根据凭证获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserByJwt(String token) {

        Date expiration = jwtUtils.getClaimByToken(token).getExpiration();
        if (expiration.before(new Date())) {
            return Result.succ(401, "登陆凭证过期", null);
        }

        String userId = jwtUtils.getClaimByToken(token).getSubject();
        User user = userService.getById(Long.parseLong(userId));
        Assert.notNull(user, "登陆凭证失效");
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("type", user.getType())
                .put("name", user.getName())
                .map());
    }

    /**
     * 根据用户 返回vo
     * @param user
     * @return
     */
    public Result getVo(User user) {
        if (user.getType() == 1) {
            // 老师
            AdminVo adminVo = new AdminVo();
            BeanUtil.copyProperties(user, adminVo);
            return Result.succ(adminVo);
        }
        else {
            // 学生
            UserVo userVo = new UserVo();
            BeanUtil.copyProperties(user, userVo);
            Integer classId = user.getClassId();
            StuClass stuClass = stuClassService.getOne(new QueryWrapper<StuClass>().eq("id", classId));
            userVo.setMajor(stuClass.getMajor());
            userVo.setClassNumber(stuClass.getClassNumber());
            userVo.setGrade(stuClass.getGrade());
            userVo.setClassId(classId);
            return Result.succ(userVo);
        }
    }



}
