package top.maserhe.common.vo;

import lombok.Data;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/24 3:07 下午
 **/
@Data
public class CourseResourceVo {

    private Integer id;

    private Integer classId;

    private String title;

    private String img;

    /**
     * 课程说明
     */
    private String description;

    private Integer teacherId;

    /**
     * 专业
     */
    private String major;


    /**
     * 年级
     */
    private String grade;




}
