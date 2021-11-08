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
 * @date 2021/11/4 7:59 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVo implements Serializable {

    private Integer id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    /**
     * type为1代表老师，type为2代表学生
     */
    private Integer type;

    private String avatar;

    private String name;


}
