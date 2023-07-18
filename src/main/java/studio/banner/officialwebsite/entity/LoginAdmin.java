package studio.banner.officialwebsite.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Wyf
 * @Date: 2021/4/27 19:40
 */
@Getter
@Setter
public class LoginAdmin {
    private String name;
    private String password;
    private Integer rememberMe;

    public LoginAdmin(String username, String password) {
        this.name = username;
        this.password = password;
    }
}
