package top.maserhe.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/6 4:11 下午
 **/
@Data
public class StuClassDTO implements Serializable {

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空")
    private String major;


    /**
     * 年级
     */
    @NotBlank(message = "年纪不能为空")
    private String grade;

}
