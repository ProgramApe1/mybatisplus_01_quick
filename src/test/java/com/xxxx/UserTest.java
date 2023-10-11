package com.xxxx;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxxx.dao.UserDao;
import com.xxxx.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserTest {
    @Autowired
    private UserDao userDao;


    @Test
    public void testSave() {
        User user = new User();
        user.setName("marry");
        user.setAge(19);
        user.setEmail("marry@qq.com");
        userDao.insert(user);
    }

    @Test
    public void testGet(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        List<User> users = userDao.selectList(lqw);
    }

    //测试逻辑删除
    @Test
    public void testDelById(){
        Long id = 1702140039263637505L;
        userDao.deleteById(id);
    }

    //测试乐观锁
    @Test
    void testUpdate(){
        //获取要修改的数据
//        User user = userDao.selectById(0);
        //修改数据
//        user.setName("jerry");
//        user.setVersion(1);
//        userDao.updateById(user);

        //模拟多个用户操作数据
        User userA = userDao.selectById(0);   //A用户 version = 1
        User userB = userDao.selectById(0);   //B用户 version = 1

        //此时两位用户的version都是 1
        userA.setName("Ivor a");
        userDao.updateById(userA);  // version = 2

        userB.setName("Ivor b");
        userDao.updateById(userB);
        //用户的数据version为1, 而数据库中的version为2, 不匹配, 无法修改
    }
} 
