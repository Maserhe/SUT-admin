package top.maserhe.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 *  老师使用的 首页作业列表
 * @author maserhe
 * @date 2021/11/30 3:55 下午
 **/
@Data
public class HomeworkTeacherVo implements Comparable{

    /**
     * 作业主键id
     */
    private Integer id;

    private Integer taskId;

    private Integer userId;

    private String taskFile;

    private Integer finalScore;

    private Integer courseId;

    private String taskName;

    private String description;

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


    /**
     * 课程名
     */
    private String title;


    /**
     * 课程的封面
     */
    private String img;


    private Integer classId;

    private String major;

    private Integer classNumber;

    private String grade;

    /**
     * 上机报告的图片
     */
    private List<String> imgs;


    /**
     * 作者
     */
    private String author;

    private List<TaskGradeVo> taskGradeVoList;

    @Override
    public int compareTo(Object o) {
        HomeworkTeacherVo vo = (HomeworkTeacherVo) o;
        int ans = grade.compareTo(vo.getGrade());
        // 1, 先比较年级
        if (ans != 0){
            return ans;
        }
        // 2， 比较专业
        ans = major.compareTo(vo.getMajor());
        if (ans != 0){
            return ans;
        }
        // 3， 比较班级
        ans = classNumber.compareTo(vo.classNumber);
        if (ans != 0) {
            return ans;
        }
        // 4， 比较姓名
        ans = author.compareTo(vo.getAuthor());
        return ans;
    }
}
