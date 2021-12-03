package top.maserhe.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 * 管理员Dto
 *
 * @author maserhe
 * @date 2021/12/2 8:48 下午
 **/
@Data
public class AdminDto {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

}
