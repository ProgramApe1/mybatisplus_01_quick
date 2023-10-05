package com.xxxx.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class User {
    private Long id;//编号
    private String name;//名称
    private Integer age;//年龄
    private String email;//邮箱

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;//逻辑删除字段, 标记用户是否被删除

    @Version
    private Integer version;//乐观锁
}
