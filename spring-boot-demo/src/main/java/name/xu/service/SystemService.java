package name.xu.service;

import org.apache.ibatis.annotations.Param;
import name.xu.entity.SystemInfo;

import java.util.List;

public interface SystemService {

    int insert(@Param("test") SystemInfo test);

    List<SystemInfo> list();

}
