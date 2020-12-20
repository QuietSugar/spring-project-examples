package name.xu.service.impl;

import name.xu.entity.Flag;
import name.xu.entity.User;
import name.xu.mapper.FlagMapper;
import name.xu.mapper.UserMapper;
import name.xu.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Resource
    private FlagMapper flagMapper;

    @Resource
    private TransactionService transactionService;

    /**
     * Required 示例
     * 外层事务
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int outerPropagationRequired(Flag flag, User user, int inner) {
        int i = flagMapper.insert(flag);
        try {
            transactionService.innerPropagationRequired(user, inner);
        } catch (Exception e) {
            //这个位置无论是否抛出,由于事务已经被设置为  rollback-only  无论内外,事务都会被回滚了,并且在最外层会有异常的抛出,请请接收
            log.error("出现了异常,这里不处理");
        }
        return i;
    }

    /**
     * Required 示例
     * 内层事务
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public int innerPropagationRequired(User user, int j) {
        int i = userMapper.insert(user);
        // 通过传入 j = 0 来制造异常
        System.out.println(10 / j);
        return i;
    }

    /**
     * REQUIRES_NEW 示例
     * 外层事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int outerPropagationRequiresNew(Flag flag, User user, int inner) {
        int i = flagMapper.insert(flag);
        try {
            transactionService.innerPropagationRequiresNew(user, inner);
        } catch (Exception e) {
            //这个位置一定要捕获异常,否则会影响 本层的事务,因为它是个异常
            log.error("捕获异常: {}", e);
        }
        return i;
    }

    /**
     * REQUIRES_NEW 示例
     * 内层事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int innerPropagationRequiresNew(User user, int j) {
        int i = userMapper.insert(user);
        // 通过传入 j = 0 来制造异常
        System.out.println(10 / j);
        return i;
    }

    /**
     * NESTED 示例
     * 外层事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int outerPropagationNested(Flag flag, User user, int inner, int outer) {
        int i = flagMapper.insert(flag);
        try {
            transactionService.innerPropagationNested(user, inner);
        } catch (Exception e) {
            //这个位置一定要捕获异常,否则会影响 本层的事务,因为它是个异常
            log.error("捕获异常: {}", e);
        }
        // 通过传入  0 来制造异常
        System.out.println(10 / outer);
        return i;
    }

    /**
     * NESTED 示例
     * 内层事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int innerPropagationNested(User user, int j) {
        int i = userMapper.insert(user);
        // 通过传入 j = 0 来制造异常
        System.out.println(10 / j);
        return i;
    }
}
