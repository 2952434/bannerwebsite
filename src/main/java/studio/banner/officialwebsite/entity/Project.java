package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/11 18:02
 * @role: Project实体
 */
@Data
@ToString
@TableName("project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "项目名称不能为空")
    private String projectName;
    @NotNull(message = "项目名称不能为空")
    private String projectBrief;
    private String projectPhotoAddress;
    private String projectAddress;
    public Project() {
    }

    public Project(String projectName, String projectBrief, String projectPhotoAddress, String projectAddress) {
        this.projectName = projectName;
        this.projectBrief = projectBrief;
        this.projectPhotoAddress = projectPhotoAddress;
        this.projectAddress = projectAddress;
    }

    public Project(Integer id, String projectName, String projectBrief, String projectPhotoAddress, String projectAddress) {
        this.id = id;
        this.projectName = projectName;
        this.projectBrief = projectBrief;
        this.projectPhotoAddress = projectPhotoAddress;
        this.projectAddress = projectAddress;
    }
}
