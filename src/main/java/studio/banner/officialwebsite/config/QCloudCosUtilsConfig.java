package studio.banner.officialwebsite.config;

/**
 * @author hyy
 * @date 2021/6/6 18:35
 * @role
 */


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studio.banner.officialwebsite.util.QCloudCosUtils;


/**
 * 腾讯云对象存储
 */
@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }
}
