package top.maserhe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.maserhe.config.FileStorageProperties;

/**
 * Description:
 * 启动类
 *
 * @author maserhe
 * @date 2021/10/28 6:23 下午
 **/
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
