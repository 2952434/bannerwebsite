package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/27 22:02
 */
@Data
@ToString
@TableName("study_direction")
public class StudyDirection {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "学习方向不能为空")
    private String direction;
    @NotNull(message = "方向介绍段落不能为空")
    private String paragraph;
}
