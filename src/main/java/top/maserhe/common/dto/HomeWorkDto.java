package top.maserhe.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/18 7:44 下午
 **/
@Data
public class HomeWorkDto {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空d")
    private Integer userId;

    /**
     * 作业id
     */
    @NotNull(message = "任务id不能为空")
    private Integer taskId;

    /**
     * 上机报告
     */
    @NotBlank(message = "上机报告不能为空")
    private String taskFile;

    /**
     * 这次上机作业的最后得分 或者老师打的分
     */
    private Integer finalScore;

    /**
     * 程序运行的截图 图片
     */
    private List<String> imgs;

}
