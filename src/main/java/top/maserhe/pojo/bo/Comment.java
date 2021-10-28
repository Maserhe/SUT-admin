package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 评论类
 *
 * @author maserhe
 * @date 2021/10/28 9:03 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 评论的用户
     */
    Integer userId;

    /**
     * 被评论的 用户
     */
    Integer entityId;

    /**
     * 被评论的课程
     */
    Integer taskId;

    /**
     * 评论的内容
     */
    String content;

}
