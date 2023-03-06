package top.maserhe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/9 7:20 下午
 **/
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
