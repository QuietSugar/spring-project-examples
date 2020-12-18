package name.xu.service.impl;

import lombok.extern.slf4j.Slf4j;
import name.xu.Application;
import name.xu.entity.User;
import name.xu.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by HuoXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    // 回滚数据(spring3 里面用@Rollback(true))
    @Transactional
    public void getUsersWithAnnotation() {
        User user = new User();
        final String name = "zhangsan";
        user.setName(name);
        Assert.assertEquals(1, userService.insert(user));
        List<User> users = userService.getUsersWithAnnotation("zhang");
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(name, users.get(0).getName());
    }

    @Test
    @Transactional
    public void getUsersWithoutAnnotation() {
        User user = new User();
        final String name = "zhangsan";
        user.setName(name);
        Assert.assertEquals(1, userService.insert(user));
        List<User> users = userService.getUsersWithoutAnnotation("zhang");
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(name, users.get(0).getName());
    }

    @Test
    @Transactional
    public void getUsersWithMap() {
        User user = new User();
        final String name = "zhangsan";
        user.setName(name);
        Assert.assertEquals(1, userService.insert(user));
        List<User> users = userService.getUsersWithMap("zhang");
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(name, users.get(0).getName());
    }

    @Test
    @Transactional
    public void insertUsers() {
        LocalDateTime now = LocalDateTime.now();
        User user1 = new User();
        String name1 = "zhangsan";
        user1.setName(name1);
        user1.setAge(1);
        user1.setCreateTime(now);
        User user2 = new User();
        String name2 = "lisi";
        user2.setName(name2);
        user2.setAge(2);
        user2.setCreateTime(now);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Assert.assertEquals(2, userService.insertUsers(users));

        List<User> users1 = userService.getUsersWithMap(name1);
        Assert.assertEquals(1, users1.size());
        Assert.assertEquals(name1, users1.get(0).getName());


        List<User> users2 = userService.getUsersWithMap(name2);
        Assert.assertEquals(1, users2.size());
        Assert.assertEquals(name2, users2.get(0).getName());
    }
}
