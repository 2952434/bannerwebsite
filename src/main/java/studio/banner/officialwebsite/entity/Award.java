package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/10 21:29
 * @role: Award实体
 */
@Data
@ToString
@TableName("award")
public class Award {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "获奖当日年份不能为空")
    private Integer year;
    @NotNull(message = "获奖当日月份不能为空")
    private Integer month;
    @NotNull(message = "获奖当日号数不能为空")
    private Integer day;
    @NotNull(message = "奖项名称不能为空")
    private String awardName;
    @NotNull(message = "获奖类型不能为空")
    private String awardClassify;
    @NotNull(message = "竞赛或项目名称不能为空")
    private String raceOrProjectName;
}
