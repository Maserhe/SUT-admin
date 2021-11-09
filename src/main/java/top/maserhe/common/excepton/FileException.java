package top.maserhe.common.excepton;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/9 6:12 下午
 **/

public class FileException extends RuntimeException{

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
