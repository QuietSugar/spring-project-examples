package name.xu.mapper;

import lombok.extern.slf4j.Slf4j;
import name.xu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * mapper 单元测试
 * 使用 @MybatisTest
 *
 * @author Created by HuoXu
 */
@RunWith(SpringRunner.class)
// 这块需要引入依赖 mybatis的测试依赖jar
@MybatisTest
// 这个注解的意义是指定了默认数据源
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class UserMapperByMybatisTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectByTidAndCode() {
        List<User> userList = userMapper.getUsersWithoutAnnotation("zahngsan", "name");

        log.debug("list: {}", userList);

    }
}
