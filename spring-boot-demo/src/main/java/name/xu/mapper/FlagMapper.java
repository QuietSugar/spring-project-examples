package name.xu.mapper;

import name.xu.entity.Flag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Created by HuoXu
 */
public interface FlagMapper {
    @Insert("insert into flag (flag,batch_code) values (#{flag.flag},#{flag.batchCode}) ")
    int insert(@Param("flag") Flag flag);

    @Select("select count(1) from  flag where batch_code = #{batchCode} ")
    int countByBatchCode(String batchCode);
}
