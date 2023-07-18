package studio.banner.officialwebsite.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.banner.officialwebsite.service.ITencentYunService;
import studio.banner.officialwebsite.util.QCloudCosUtils;

import java.io.File;

/**
 * @author hyy
 * @date 2021/6/6 19:39
 * @role
 */
@Service
public class TencentYunServiceImpl implements ITencentYunService {
    @Autowired
    private QCloudCosUtils qCloudCosUtils;

    @Override
    public String upload(MultipartFile multipartFile) {
        return qCloudCosUtils.upload(multipartFile);
    }

    @Override
    public String upload(File file) {
        return qCloudCosUtils.upload(file);
    }

    @Override
    public boolean delete(String fileName) {
        return qCloudCosUtils.deleteFile(fileName);
    }
}
