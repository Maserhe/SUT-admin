package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *  用户上传的作业
 * @author maserhe
 * @date 2021/10/28 9:39 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Homework {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 上传作业的用户id
     */
    Integer userId;


    /**
     * 课程的id
     */
    Integer taskId;


    /**
     * 最总的成绩得分
     */
    Integer finalScore;
}
