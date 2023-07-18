package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author hyy
 * @date 2021/5/11 17:06
 * @role: ManagementSystem实体
 */
@Data
@ToString
@TableName("management_system")
public class ManagementSystem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "制度名称不能为空")
    private String managementSystemName;
    @NotNull(message = "管理制度内容不可为空")
    private String managementSystemContent;

    public ManagementSystem(String managementSystemName) {
        this.managementSystemName = managementSystemName;
    }
}
