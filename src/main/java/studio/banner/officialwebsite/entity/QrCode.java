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
 * @author hyy
 * @date 2021/5/17 11:51
 * @role 二维码实体
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("qr_code")
public class QrCode {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "图片地址不可以为空")
    private String photoAddress;
    @NotNull(message = "图片名称不能为空")
    private String photoName;
}
