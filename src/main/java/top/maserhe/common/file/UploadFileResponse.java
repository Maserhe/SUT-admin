package top.maserhe.common.file;

import lombok.Data;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/9 5:55 下午
 **/
@Data
public class UploadFileResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

}
