package top.maserhe.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/27 7:09 下午
 **/
@Data
public class HomeworkScoreVo {

    private Integer id;

    private Integer userId;

    private Integer taskId;

    private Integer courseId;

    private String taskFile;

    private Integer finalScore;

    private String description;

    private String taskName;

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

    private Integer gradeId;

    private Integer entityId;

    private Integer score;

    private String comment;

    private List<String> imgs;

    private String title;

    private Integer teacherId;

    private String author;

}
