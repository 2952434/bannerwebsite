package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/26 16:45
 */
@Data
@ToString
@TableName("project_personnel")
public class ProjectPersonnel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "成员id不能为空")
    private Integer memberId;
    @NotNull(message = "项目id不能为空")
    private Integer projectId;

    public ProjectPersonnel(Integer memberId, Integer projectId) {
        this.memberId = memberId;
        this.projectId = projectId;
    }

    public ProjectPersonnel() {
    }
}
