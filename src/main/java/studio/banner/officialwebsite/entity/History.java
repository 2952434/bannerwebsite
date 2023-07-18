package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/27 18:41
 */
@Data
@ToString
@TableName("history")
public class History {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "时间：年，不能为空")
    private Integer year;
    @NotNull(message = "时间：月，不能为空")
    private Integer month;
    @NotNull(message = "时间：日，不能为空")
    private Integer day;
    @NotNull(message = "事件概括不能为空")
    private String incident;
    @NotNull(message = "标题不能为空")
    private String title;
}
