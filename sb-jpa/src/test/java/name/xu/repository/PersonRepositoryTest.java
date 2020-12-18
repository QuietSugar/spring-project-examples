package name.xu.repository;

import lombok.extern.slf4j.Slf4j;
import name.xu.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author Created by HuoXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void test() {
        log.info("通过name开头和ID搜索: {}", personRepository.findByNameStartingWithAndIdLessThan("g", 6));
        log.info("通过name结尾和ID搜索: {}", personRepository.findByNameEndingWithAndIdLessThan("g", 6));
        log.info("通过搜索Email和Age: {}", personRepository.readByEmailInOrAgeLessThan(Arrays.asList("123@qq.com"), 25));
        //log.info("通过Address和ID搜索: {}", personRepository.findByAddress_IdGreaterThan(1));
        log.info("通过ID最大搜索: {}", personRepository.getMaxIdPerson());
        log.info("通过占位符参数搜索: {}", personRepository.queryAnnotationParam1("liqingfeng", "123@qq.com"));
        log.info("通过命名参数搜索: {}", personRepository.queryAnnotationParam2("lqf"));
        log.info("模糊搜索: {}", personRepository.queryAnnotationLikeParam2("li"));
        log.info("统计行数: {}", personRepository.getTotalRow());

    }
}
