package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Author: Re
 * @Date: 2021/5/17 22:14
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("journalism_photo")
public class JournalismPhoto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "关联的项目id不能为空")
    private Integer journalismId;
    @NotEmpty(message = "图片地址不能为空")
    private String photoAddress;
    @NotEmpty(message = "关联的新闻段落不能为空")
    private Integer journalismParagraphNumber;
    @Size(min = 0,max = 1)
    private Integer isCarousel;
}
