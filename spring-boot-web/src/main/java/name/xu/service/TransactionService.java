package name.xu.service;

import name.xu.entity.Flag;
import name.xu.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务测试
 */
public interface TransactionService {
    int outerPropagationRequired(Flag flag, User user, int inner);

    int innerPropagationRequired(User user, int j);

    int outerPropagationRequiresNew(Flag flag, User user, int inner);

    int innerPropagationRequiresNew(User user, int j);

    int outerPropagationNested(Flag flag, User user, int inner, int outer);

    int innerPropagationNested(User user, int j);
}
