package com.xxxx.domain;



import lombok.*;
import org.springframework.stereotype.Component;

/**
 * 数据库Book表的实体类
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //有参构造
@Component
public class Book {
//    @TableId(type = IdType.AUTO)//修改id 为自增
    private Integer id;//编号
    private String type;//书籍类型
    private String name;//书名
    private String description;//描述
}
