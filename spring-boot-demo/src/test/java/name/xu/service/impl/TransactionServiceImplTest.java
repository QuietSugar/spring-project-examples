package name.xu.service.impl;

import lombok.extern.slf4j.Slf4j;
import name.xu.Application;
import name.xu.entity.Flag;
import name.xu.entity.User;
import name.xu.service.FlagService;
import name.xu.service.TransactionService;
import name.xu.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Created by HuoXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;
    @Autowired
    FlagService flagService;

    /**
     * REQUIRED
     */
    @Test
    public void propagationRequired() {
        LocalDateTime now = LocalDateTime.now();
        String batchCode = UUID.randomUUID().toString();
        User zhangSan = getZhangSan();
        zhangSan.setCreateTime(now);
        Flag flag = getFlag();
        flag.setBatchCode(batchCode);

        // 模拟未出现异常的情况
        transactionService.outerPropagationRequired(flag, zhangSan, 1);
        Assert.assertEquals(1, userService.countByBatchCode(batchCode));
        Assert.assertEquals(1, flagService.countByBatchCode(batchCode));

        // 模拟出现异常的情况
        String batchCodeNew = UUID.randomUUID().toString();
        User zhangSanNew = getZhangSan();
        zhangSanNew.setCreateTime(now);
        Flag flagNew = getFlag();
        flagNew.setBatchCode(batchCodeNew);

        try {
            transactionService.outerPropagationRequired(flagNew, zhangSanNew, 0);
        } catch (Exception e) {
            // 这里是调用的源头,会有异常抛出,需要接收
            log.error("出现了异常: {}", e);
        }
        // 内外一起插入失败
        Assert.assertEquals(0, flagService.countByBatchCode(batchCodeNew));
        Assert.assertEquals(0, userService.countByBatchCode(batchCodeNew));
    }


    /**
     * REQUIRES_NEW
     */
    @Test
    public void propagationRequiresNew() {
        LocalDateTime now = LocalDateTime.now();
        String batchCode = UUID.randomUUID().toString();
        User zhangSan = getZhangSan();
        zhangSan.setCreateTime(now);
        Flag flag = getFlag();
        flag.setBatchCode(batchCode);

        // 模拟未出现异常的情况
        transactionService.outerPropagationRequiresNew(flag, zhangSan, 1);
        Assert.assertEquals(1, userService.countByBatchCode(batchCode));
        Assert.assertEquals(1, flagService.countByBatchCode(batchCode));

        // 模拟出现异常的情况
        String batchCodeNew = UUID.randomUUID().toString();
        User zhangSanNew = getZhangSan();
        zhangSanNew.setCreateTime(now);
        Flag flagNew = getFlag();
        flagNew.setBatchCode(batchCodeNew);

        try {
            transactionService.outerPropagationRequiresNew(flagNew, zhangSanNew, 0);
        } catch (Exception e) {
            // 这里是调用的源头,会有异常抛出,需要接收
            log.error("出现了异常: {}", e);
        }
        // 外部成功,内部失败
        Assert.assertEquals(1, flagService.countByBatchCode(batchCodeNew));
        Assert.assertEquals(0, userService.countByBatchCode(batchCodeNew));
    }

    /**
     * REQUIRES_NEW
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * JpaDialect does not support savepoints - check your JPA provider's capabilities
     * Hibernate 的JPA 不支持 这种模式
     *
     * 预期行为
     * 内部回滚,不影响外部 (只要捕获了异常),这一点区别于 REQUIRED
     * 外部回滚会导致内部回滚
     */
    @Test
    public void propagationNested() {
        //    Hibernate 的JPA 不支持,先不测试
    }


    public static User getZhangSan() {
        User zhangsan = new User();
        String uuid = UUID.randomUUID().toString();
        String name1 = "zhangsan";
        zhangsan.setName(name1);
        zhangsan.setAge(1);
        zhangsan.setUuid(uuid);
        return zhangsan;
    }

    public static Flag getFlag() {
        Flag flag = new Flag();
        flag.setFlag("start");
        return flag;
    }
}
