package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 课程类
 *
 * @author maserhe
 * @date 2021/10/28 9:04 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 课程标题
     */
    String title;

    /**
     * 课程图片
     */
    String img;

    /**
     * 课程的描述
     */
    String description;

}
