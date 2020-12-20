package name.xu.service;

import name.xu.entity.Flag;

public interface FlagService {
    int insert(Flag flag);
    int countByBatchCode(String batchCode);
}
