package studio.banner.officialwebsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@MapperScan("studio.banner.officialwebsite.mapper")
public class OfficialWebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficialWebsiteApplication.class, args);
    }
}
