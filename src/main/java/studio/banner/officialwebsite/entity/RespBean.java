package studio.banner.officialwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Re
 * @Date: 2021/5/12 16:18
 * @role: 响应实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;
    public static RespBean build() {
        return new RespBean();
    }
    public static RespBean ok(Object obj) {
        return new RespBean(200,null,obj);
    }
    public static RespBean ok(String msg) {
        return new RespBean(200,msg,null);
    }
    public static RespBean ok(String msg,Object obj) {
        return new RespBean(200,msg,obj);
    }
    public static RespBean error(String msg,Object obj) {
        return new RespBean(500,msg,obj);
    }
    public static RespBean error(String msg) {
        return new RespBean(500,msg,null);
    }
    public static RespBean error(Object obj) {
        return new RespBean(500,null,obj);
    }
}
