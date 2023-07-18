package studio.banner.officialwebsite.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author hyy
 * @date 2021/6/6 19:38
 * @role
 */
public interface ITencentYunService {
    /**
     * 处理浏览器文件上传请求
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);

    /**
     * 处理普通文件上传
     * @param file
     * @return
     */
    String upload(File file);

    /**
     * 根据文件名删除文件
     * @param fileName
     * @return
     */
    boolean delete(String fileName);
}
