package com.xxxx.domain.query;

import com.xxxx.domain.Book;
import lombok.Data;

@Data
public class BookQuery extends Book {
    private Integer id2;//表示id的上限
}
