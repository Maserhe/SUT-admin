package top.maserhe.controller;


import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.maserhe.common.lang.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/img")
public class ImgController {


    /**
     * 上传文件， 返回文件的下载连接
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {

        Assert.notNull(file, "上传文件不能为空");
        // 1, 将文件保存到本地

        // 2，将图片路径保存下来

        // 3，返回图片访问的url

        return Result.succ(null);
    }






}
