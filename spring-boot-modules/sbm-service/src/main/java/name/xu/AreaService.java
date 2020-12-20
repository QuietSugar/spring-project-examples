package name.xu;

import name.xu.entity.Area;

import java.util.List;

public interface AreaService {
    int insert(Area area);

    List<Area> list();

    String findNameByCode(String batchCode);
}
