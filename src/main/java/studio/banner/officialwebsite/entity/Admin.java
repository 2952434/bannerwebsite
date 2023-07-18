package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author hyy
 * @date 2021/5/23 9:30
 * @role
 */
@Data
@ToString
@NoArgsConstructor
@TableName("administrator")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "用户名不可以为空")
    private String name;
    @NotNull(message = "密码不可以为空")
    private String password;
    @NotNull(message = "权限不可以为空")
    private String role;
}
