package name.xu.mapper;

import name.xu.entity.Area;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AreaMapper {

    @Insert("insert into sbm_area(code,name,parent_code,level)" +
            " values (#{c.code},#{c.name},#{c.parentCode},#{c.level})")
    @Options(useGeneratedKeys = true, keyProperty = "c.id", keyColumn = "id")
    int insert(@Param("c") Area c);

    @Select("select * from sbm_area")
    List<Area> list();

    @Select("select name from sbm_area where code = #{code}")
    String findNameByCode(@Param("code") String code);

    @Select("select code, name, parent_code, level from sbm_area where name = #{name}")
    @Results({
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentCode", column = "parent_code"),
            @Result(property = "level", column = "level"),
    })
    List<Area> findCodeByName(@Param("name") String name);

    @Select("select code, name, parent_code, level from sbm_area where name like concat('%',#{name},'%')")
    @Results({
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentCode", column = "parent_code"),
            @Result(property = "level", column = "level"),
    })
    List<Area> findCodeByLikeName(@Param("name") String name);
}
