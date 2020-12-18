package name.xu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import name.xu.service.SystemService;
import name.xu.entity.SystemInfo;
import name.xu.mapper.SystemMapper;

import java.util.List;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemMapper mapper;

    @Override
    public int insert(SystemInfo test) {
        // TODO Auto-generated method stub
        return mapper.insert(test);
    }

    @Override
    public List<SystemInfo> list() {
        // TODO Auto-generated method stub
        return mapper.list();
    }

}
