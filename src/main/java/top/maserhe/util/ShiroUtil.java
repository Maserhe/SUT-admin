package top.maserhe.util;

import top.maserhe.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/1 2:06 下午
 **/
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
