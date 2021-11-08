package top.maserhe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    private String taskName;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 学生是否可以查看
     */
    private Integer viewPermission;

    /**
     * 学生是否可以打分
     */
    private Integer scorePermission;

    private LocalDateTime startTime;

    private LocalDateTime stopTime;


}
