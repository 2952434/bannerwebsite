package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/6/8 17:16
 */
@Data
@ToString
@TableName("theme_photo")
public class ThemePhoto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "图片链接不能为空")
    private String url;
    @NotNull(message = "图片的位置")
    private Integer photoSite;
    @NotNull(message = "图片所属月份")
    private int photoMonth;
    private String css = "white";

    public ThemePhoto(Integer id) {
        this.id = id;
    }

    public ThemePhoto() {
    }
}
