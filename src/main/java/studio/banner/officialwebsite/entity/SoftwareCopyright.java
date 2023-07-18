package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/19 15:35
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("software_copyright")
public class SoftwareCopyright {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "软件名称不能为空")
    private String softwareCopyrightName;
    @NotNull(message = "软著申请成功年份不能为空")
    private Integer year;
    @NotNull(message = "软著申请成功月份不能为空")
    private Integer month;
    @NotNull(message = "软著申请成功号数不能为空")
    private Integer day;
}
