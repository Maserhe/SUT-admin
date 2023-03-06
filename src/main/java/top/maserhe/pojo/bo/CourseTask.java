package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 课程任务
 *
 * @author maserhe
 * @date 2021/10/28 9:28 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTask {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 课程id
     */
    Integer courseId;


    /**
     * 作业名称
     */
    Integer taskName;

    /**
     * 描述信息
     */
    String description;

    /**
     * 普通用户的查看的权限
     */
    Integer viewPermission;


    /**
     * 普通用户打分的权限
     */
    Integer scorePermission;
}
