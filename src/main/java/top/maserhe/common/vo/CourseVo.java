package top.maserhe.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Description:
 *     课程Vo类
 * @author maserhe
 * @date 2021/11/10 7:21 下午
 **/
@Data
public class CourseVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer classId;

    private String title;

    private String img;

    /**
     * 课程说明
     */
    private String description;

    private Integer teacherId;

    private String teacherName;

}
