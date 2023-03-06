package top.maserhe.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/9 3:20 下午
 **/
@Data
public class CourseDto {

    @NotNull( message = "班级号不应为空")
    private Integer classId;

    @NotBlank( message = "标题不应为空")
    private String title;

    @NotBlank( message = "课程封面不应为空")
    private String img;

    /**
     * 课程 描述
     */
    private String description;

    private Integer teacherId;

}
