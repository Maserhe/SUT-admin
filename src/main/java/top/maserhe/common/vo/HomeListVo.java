package top.maserhe.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.maserhe.entity.Img;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/24 9:10 下午
 **/
@Data
public class HomeListVo {

    /**
     * 提交上机报告的id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;



    private String taskFile;

    private Integer taskId;

    private Integer courseId;


    /**
     * 学生是否可以查看
     */
    private Integer viewPermission;

    /**
     * 学生是否可以打分
     */
    private Integer scorePermission;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime stopTime;


    private String title;

    private String taskName;

    private String description;


    private String name;

    private String avatar;

    private List<Img> imgs;



}
