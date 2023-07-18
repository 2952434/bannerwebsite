package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/27 9:18
 */
@Data
@ToString
@TableName("award_personnel")
public class AwardPersonnel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "关联成员id不能为空")
    private Integer memberId;
    @NotNull(message = "关联项目id不能为空")
    private Integer awardId;

    public AwardPersonnel() {
    }

    public AwardPersonnel(Integer memberId, Integer awardId) {
        this.memberId = memberId;
        this.awardId = awardId;
    }
}
