package top.maserhe.common.vo;

import lombok.Data;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/30 4:22 下午
 **/
@Data
public class TaskGradeVo {

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

    private String author;


}
