package name.xu.mapper;

import name.xu.entity.BasicBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 批量操作的示例
 *
 * @author Created by HuoXu
 */
public interface BatchOperationMapper {

    /**
     * 批量更新
     *
     * 需要 allowMultiQueries=true 支持
     */
    @Update({
            "<script>",
            "<foreach collection='list' item='item' separator=';'>",
            "update basicBeanList set string_type = #{item.stringType} where id = #{item.id}",
            "</foreach>",
            "</script>",
    })
    void updateBatch(@Param("list") List<BasicBean> basicBeanList);
}
