package top.maserhe.shiro;

import lombok.Data;
import java.io.Serializable;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/1 1:58 下午
 **/
@Data
public class AccountProfile implements Serializable {

    private Integer id;

    private String username;

    /**
     * type为1代表老师，type为2代表学生
     */
    private Integer type;

    private String avatar;

}
