package studio.banner.officialwebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Re
 * @Date: 2021/5/7 13:10
 */
@Data
@ToString
@TableName("member")
public class Member {
    @TableId(type = IdType.AUTO)
    Integer id;
    @NotNull(message = "成员名不能为空")
    String memberName;
    @NotNull(message = "成员博客链接不能为空")
    String memberBlog;
    @NotNull(message = "成员学习方向不能为空")
    String memberDirection;
    String memberResponsibility;
    @NotNull(message = "成员年级不能为空")
    Integer memberGrade;
    String memberPhoto;
    String memberIntroductory;

    public Member(String memberName, String memberBlog, String memberDirection, String memberResponsibility, Integer memberGrade, String memberPhoto, String memberIntroductory) {
        this.memberName = memberName;
        this.memberBlog = memberBlog;
        this.memberDirection = memberDirection;
        this.memberResponsibility = memberResponsibility;
        this.memberGrade = memberGrade;
        this.memberPhoto = memberPhoto;
        this.memberIntroductory = memberIntroductory;
    }

    public Member(Integer id, String memberPhoto) {
        this.id = id;
        this.memberPhoto = memberPhoto;
    }

    public Member() {
    }

    public Member(Integer id, String memberName, String memberBlog, String memberDirection, String memberResponsibility, Integer memberGrade, String memberPhoto, String memberIntroductory) {
        this.id = id;
        this.memberName = memberName;
        this.memberBlog = memberBlog;
        this.memberDirection = memberDirection;
        this.memberResponsibility = memberResponsibility;
        this.memberGrade = memberGrade;
        this.memberPhoto = memberPhoto;
        this.memberIntroductory = memberIntroductory;
    }

    public Member(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }
}
