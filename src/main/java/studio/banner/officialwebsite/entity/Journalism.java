package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/8 20:01
 * @role: Journalism实体
 */
@Data
@ToString
@TableName("journalism")
@NoArgsConstructor
@AllArgsConstructor
public class Journalism {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "发生年份不能为空")
    @TableField("journalism_time_year")
    private Integer journalismTimeYear;
    @NotEmpty(message = "发生月份不能为空")
    @TableField("journalism_time_month")
    private Integer journalismTimeMonth;
    @NotEmpty(message = "发生号数不能为空")
    @TableField("journalism_time_day")
    private Integer journalismTimeDay;
    @NotEmpty(message = "新闻标题不能为空")
    @TableField("journalism_title")
    private String journalismTitle;
    @NotNull(message = "新闻介绍不能为空")
    private String journalismIntroduce;
}
