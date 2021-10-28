package top.maserhe.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/10/28 9:00 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 主键id
     */
    Integer id;

    /**
     * 用户名
     */
    String username;

    /**
     * 用户密码
     */
    String password;

    /**
     * 0 代表老师， 1 代表学生
     */
    Integer type;

}
