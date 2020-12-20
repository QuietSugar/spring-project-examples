package name.xu.impl;

import name.xu.AreaService;
import name.xu.entity.Area;
import name.xu.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource
    AreaMapper areaMapper;

    @Override
    public int insert(Area area) {
        return areaMapper.insert(area);
    }

    @Override
    public List<Area> list() {
        return areaMapper.list();
    }

    @Override
    public String findNameByCode(String code) {
        return areaMapper.findNameByCode(code);
    }
}
