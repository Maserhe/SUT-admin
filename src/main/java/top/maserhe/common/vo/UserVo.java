package top.maserhe.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/4 7:58 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private Integer id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    /**
     * type为1代表老师，type为2代表学生
     */
    private Integer type;

    private String avatar;

    private String name;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级的主键id
     */
    private Integer classId;


    /**
     * 几班， 例如 2班
     */
    private Integer classNumber;

    /**
     * 年级
     */
    private String grade;

}
