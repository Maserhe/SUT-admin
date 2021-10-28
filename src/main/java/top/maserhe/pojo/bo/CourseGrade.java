package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *   作业的打分
 * @author maserhe
 * @date 2021/10/28 9:05 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseGrade {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 打分的用户
     */
    Integer userId;

    /**
     * 被打分的人
     */
    Integer entityId;

    /**
     * 被打分的作业
     */
    Integer taskId;

    /**
     * 打得分数
     */
    Integer score;


}
