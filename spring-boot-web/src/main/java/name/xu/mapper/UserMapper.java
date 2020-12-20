package name.xu.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import name.xu.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author Created by HuoXu
 */
public interface UserMapper {
    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUsersWithoutAnnotation")
    List<User> getUsersWithoutAnnotation(
            @Param("name") String name,
            @Param("orderByColumn") String orderByColumn
    );

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUsersWithAnnotation")
    List<User> getUsersWithAnnotation(
            @Param("name") String name,
            @Param("orderByColumn") String orderByColumn
    );

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUsersWithMap")
    List<User> getGetUsersWithMap(
            @Param("name") String name,
            @Param("orderByColumn") String orderByColumn
    );

    @Insert("insert into user (name,uuid) values (#{user.name},#{user.uuid}) ")
    int insert(@Param("user") User user);

    @Insert({"<script> ",
            "insert into user(name,age,create_time) values ",
            "  <foreach collection='list' item='user' separator=',' > ",
            "  (#{user.name},#{user.age},#{user.createTime})",
            "  </foreach> </script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUsers(@Param("list") List<User> list);

    @Select("select count(1) from  user where batch_code = #{batchCode} ")
    int countByBatchCode(String batchCode);

    class UserSqlBuilder {
        /**
         * 不使用注解
         * 该处必须列出所有参数(不论是否使用),通过参数名称匹配
         * !!!! 特别注意,在较新的 mybatis(可能是3.4.6),这种方式可能会失效,参数经过反射,丢失了名称,变成了 arg0-argn 或者 para0-paran,请换用其它方式
         */
        public static String buildGetUsersWithoutAnnotation(
                final String name,
                final String orderByColumn) {
            return new SQL() {{
                SELECT("*");
                FROM("user");
                WHERE("name like concat('%',#{name},'%')");
                ORDER_BY(orderByColumn);
            }}.toString();
        }

        /**
         * 使用注解
         * 只用列出显式使用的参数,仅在sql中使用的,可以不用列出
         */
        public static String buildGetUsersWithAnnotation(@Param("orderByColumn") final String orderByColumn) {
            return new SQL() {{
                SELECT("*");
                FROM("user");
                WHERE("name like concat('%',#{name},'%')");
                ORDER_BY(orderByColumn);
            }}.toString();
        }

        /**
         * 使用map
         * 使用一个 map ,这个 map 里面有所有的参数
         */
        public static String buildGetUsersWithMap(final Map map) {
            String orderByColumn = (String) map.get("orderByColumn");
            return new SQL() {{
                SELECT("*");
                FROM("user");
                WHERE("name like concat('%',#{name},'%')");
                ORDER_BY(orderByColumn);
            }}.toString();
        }
    }
}
