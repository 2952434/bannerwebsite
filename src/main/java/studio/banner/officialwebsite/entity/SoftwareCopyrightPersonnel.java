package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/27 8:08
 */
@Data
@ToString
@TableName("software_copyright_personnel")
public class SoftwareCopyrightPersonnel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "软著著作人员不能为空")
    private Integer memberId;
    @NotNull(message = "软著信息id不能为空")
    private Integer softwareCopyrightId;

    public SoftwareCopyrightPersonnel(Integer memberId, Integer softwareCopyrightId) {
        this.memberId = memberId;
        this.softwareCopyrightId = softwareCopyrightId;
    }

    public SoftwareCopyrightPersonnel() {
    }
}
