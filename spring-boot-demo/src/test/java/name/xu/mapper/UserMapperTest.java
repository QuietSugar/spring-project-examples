package name.xu.mapper;

import lombok.extern.slf4j.Slf4j;
import name.xu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import name.xu.Application;

import java.util.List;

/**
 * @author Created by HuoXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByTidAndCode() {
        List<User> userList = userMapper.getUsersWithoutAnnotation("zahngsan", "name");

        log.debug("list", userList);

    }
}
