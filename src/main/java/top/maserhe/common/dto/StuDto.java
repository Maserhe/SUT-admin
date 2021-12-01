package top.maserhe.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Description:
 *  添加用户的的注册类
 * @author maserhe
 * @date 2021/11/7 2:56 下午
 **/
@Data
public class StuDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * type为1代表老师，type为2代表学生
     */
    private Integer type;

    private String avatar;

    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 班级号码
     */
    private Integer classNumber;
}
