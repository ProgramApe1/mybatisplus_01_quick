package com.xxxx;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.dao.BookDao;
import com.xxxx.domain.Book;
import com.xxxx.domain.query.BookQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookTests {
    @Autowired
    private BookDao bookDao;

    /**
     * 测试条件查询
     * lt:小于        gt:大于
     * ge:大于等于     le:小于等于
     * isnull:为空    isNotNull:非空
     * eq:等于        ne:不等于
     */
    @Test
    void testGetAll() {
        /* 1.QueryWrapper条件构造器 */
//        QueryWrapper<Book> wrapper  = new QueryWrapper<>();
        //lt(小于)
//        wrapper.lt("id",3);   //where id < 3
//        List<Book> books = bookDao.selectList(wrapper);
//        for (Book book :books) {
//            System.out.println(book);
//        }
        //gt(大于)
//        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        //链式编程格式
//        wrapper.lambda().gt(Book::getId, 3).lt(Book::getId, 10); // id > 3 and id < 10
        //and
//        wrapper.lambda().gt(Book::getId, 3);// id > 3
//        wrapper.lambda().lt(Book::getId, 10);// id < 10
        // or(或者)
//        wrapper.lambda().gt(Book::getId, 15).or().lt(Book::getId, 5);// id > 15 or id < 5
//        List<Book> books = bookDao.selectList(wrapper);
//        for (Book book : books) {
//            System.out.println(book);
//        }
        /* 2.Lambda条件构造器 */
        //QueryWrapper<Book> wrapper = new QueryWrapper<>();
        //wrapper.lambda().gt(Book::getId, 15).or().lt(Book::getId, 5);// id > 15 or id < 5

        //模拟页面传递过来的数据
        BookQuery bq = new BookQuery();
//        bq.setId(10);//表示id的下限
//        bq.setId2(5);//表示id的上限

        //null判定
        //1.lambda
//        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
//        //设置查询条件
//        lqw.ge(null != bq.getId(), Book::getId, bq.getId());
//        lqw.le(null != bq.getId2(), Book::getId, bq.getId2());
//        //查询投影
////        lqw.select(Book::getId, Book::getName); //只显示查询数据的id, name字段
//        //分组查询
//        lqw.groupBy(Book::getType);
//        //遍历查询
//        List<Book> books = bookDao.selectList(lqw);
//        for (Book book : books) {
//            System.out.println(book);
//        }

        //2.queryWrapper
//        QueryWrapper qw = new QueryWrapper();
//        //查询条件
////        qw.le("id", bq.getId2());//id <= 5
////        qw.select("id","name");
////        List<Book> books = bookDao.selectList(qw);
//        qw.select("count(type)");//查询数据总数
//        qw.groupBy("id"); //分组
//        List bookList = bookDao.selectMaps(qw);
//        System.out.println("==========查询结果==========");
//        System.out.println(bookList);
    }

    /**
     * 测试添加操作
     */
    @Test
    void testSave() {
        Book book = new Book();
        book.setType("计算机");
        book.setName("springBoot-MybatisPlus");
        book.setDescription("一代宗师");
        bookDao.insert(book);
    }

    /**
     * 测试删除操作
     */
    @Test
    void testDeleteById() {
        int id = 956706820;
        bookDao.deleteById(id);
    }

    //根据id批量删除
    @Test
    void testDeleteByList(){
        List<Integer> list = new ArrayList<>();
        list.add(18);
        list.add(19);
        list.add(20);
        list.add(772210690);
        bookDao.deleteBatchIds(list);
    }

    //多记录查询
    @Test
    void testGetByList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        bookDao.selectBatchIds(list);
    }

    /**
     * 测试修改操作
     */
    @Test
    void testUpdateById() {
        Book book = new Book();
        book.setId(18);
        book.setType("java");
        book.setName("springBoot-MybatisPlus");
        book.setDescription("一代宗师");
        bookDao.updateById(book);
    }

    /**
     * 测试根据id查询
     */
    @Test
    void testGetById() {
        int id = 956706819;
        Book book = bookDao.selectById(id);
        System.out.println(book);
    }

    @Test
    void testGetByPage() {
        int current = 1;    //当前页码
        int size = 2;   //每页显示数
        IPage page = new Page(current, size);
        bookDao.selectPage(page, null);
        System.out.println("当前页码值:" + page.getCurrent());
        System.out.println("每页显示数:" + page.getSize());
        System.out.println("总页数:" + page.getPages());
        System.out.println("总数据数:" + page.getTotal());
        System.out.println("当前页码 " + current + " 显示的" + size + "条数据:");
        List<Book> records = page.getRecords();
        for (Book book : records) {
            System.out.println(book);
        }
    }

    @Test
    void testGetByLambda(){
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        //eq
//        lqw.eq(Book::getName, "SpringMVC入门教程");
        //between
//        lqw.between(Book::getId, 1, 6);
        //模糊匹配 like
//        lqw.like(Book::getName, "s");
//        lqw.likeLeft(Book::getName, "S");
        lqw.likeRight(Book::getName, "S");
        List<Book> books = bookDao.selectList(lqw);
        //查询
        for (Book book :books) {
            System.out.println(book);
        }
    }

}
