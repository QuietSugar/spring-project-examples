package name.xu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import name.xu.entity.SystemInfo;

import java.util.List;

@Mapper
public interface SystemMapper {

    @Insert("insert into ts_test(first_name,last_name) values (#{test.firstName},#{test.lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "test.id", keyColumn = "id")
    int insert(@Param("test") SystemInfo test);

    @Select("select * from ts_test")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    List<SystemInfo> list();

}
