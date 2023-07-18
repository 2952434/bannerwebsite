package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/24 17:35
 */
@Data
@TableName("journalism_paragraph")
public class JournalismParagraph {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "新闻段落不能为空")
    private String content;
    @NotNull(message = "关联的新闻表id不能为空")
    private Integer journalismId;
}
