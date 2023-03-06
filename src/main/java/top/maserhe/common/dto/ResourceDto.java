package top.maserhe.common.dto;

import lombok.Data;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/24 2:51 下午
 **/
@Data
public class ResourceDto {

    /**
     * 课程id
     */
    private Integer courseId;


    private String file;

    /**
     * 文件名
     */
    private String fileName;



}
