package name.xu.service.impl;

import name.xu.entity.Flag;
import name.xu.mapper.FlagMapper;
import name.xu.service.FlagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("flagService")
public class FlagServiceImpl implements FlagService {
    @Resource
    FlagMapper flagMapper;

    @Override
    public int insert(Flag flag) {
        return flagMapper.insert(flag);
    }

    @Override
    public int countByBatchCode(String batchCode) {
        return flagMapper.countByBatchCode(batchCode);
    }
}
