package top.maserhe.common.dto;

import lombok.Data;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/27 5:03 下午
 **/
@Data
public class TaskGradeDto {

    /**
     * 打分的人
     */
    private Integer userId;

    /**
     * 被打分的人
     */
    private Integer homeworkId;

    /**
     * 对某个作业进行打分
     */
    private String comment;


    private Integer score;
}
