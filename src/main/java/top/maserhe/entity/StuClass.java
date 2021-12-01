package top.maserhe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Comparator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StuClass implements Serializable , Comparable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Override
    public int compareTo(Object o) {
        StuClass t = (StuClass) o;
        String str1 = this.grade + this.major ;
        String str2 = t.grade + t.major;
        return str1.compareTo(str2);
    }

    /**
     * 专业
     */
    private String major;


    /**
     * 年级
     */
    private String grade;


}
