package top.maserhe.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/18 3:59 下午
 **/
@Data
public class CourseTaskVo {

    private Integer id;

    private Integer courseId;

    private String taskName;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 学生是否可以查看
     */
    @NotNull(message = "学生查看权限不能为空")
    private Integer viewPermission;

    /**
     * 学生是否可以打分
     */
    @NotNull(message = "打分权限不能为空")
    private Integer scorePermission;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime stopTime;

    private Boolean isUpload;

    /**
     * 权重
     */
    private Double weight;
}
