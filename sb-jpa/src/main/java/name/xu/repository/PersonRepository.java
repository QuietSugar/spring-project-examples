package name.xu.repository;

/**
 * @author Created by HuoXu
 */

import name.xu.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//1.实际上Repository是一个口接口，没有提供任何方法，是一个标记接口
//2.实现了Repository接口就会被spring IOC容器识别为Repository Bean
//		会被纳入IOC容器中
//3.Repository接口也可以同@RepositoryDefinition 注解代替，效果是一样的
//4.接口中的泛型：第一个是那个实体类的Repository，第二个是实体类的主键的类型
//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)

/**
 * 在Repository接口中申明方法 1.申明方法需要符合一定的规范 2.查询方法需要以 find | read | get开头 3.涉及查询条件时
 * 需要用条件关键字连接 4.属性首字母大写 5.支持级联属性
 * 6.AddressId若当前实体类中有属性，则优先使用该属性，若想要使用级联属性，需要用下划线隔开Address_Id
 */

public interface PersonRepository extends Repository<Person, Integer> {
    // select p from Person where p.name = ?
    Person getByName(String name);

    List<Person> findByNameStartingWithAndIdLessThan(String name, Integer id);

    // where name like %? and id < ?
    List<Person> findByNameEndingWithAndIdLessThan(String name, Integer id);

    // where email in ? age < ?
    List<Person> readByEmailInOrAgeLessThan(List<String> emails, int age);

    // 级联属性查询
    // where address.id > ?
    //List<Person> findByAddress_IdGreaterThan(Integer is);

    // 可以使用@Query注解在其value属性中写JPA语句灵活查询
    @Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
    Person getMaxIdPerson();

    // 在@Query注解中使用占位符
    @Query(value = "SELECT p FROM Person p where p.name = ?1 and p.email = ?2")
    List<Person> queryAnnotationParam1(String name, String email);

    // 使用命名参数传递参数
    @Query(value = "SELECT p FROM Person p where p.name = :name")
    List<Person> queryAnnotationParam2(@Param("name") String name);

    // SpringData可以在参数上添加%
    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    List<Person> queryAnnotationLikeParam(String name);

    // SpringData可以在参数上添加%
    @Query("SELECT p FROM Person p WHERE p.name LIKE %:name%")
    List<Person> queryAnnotationLikeParam2(@Param("name") String name);

    //在@Query注解中添加nativeQuery=true属性可以使用原生的SQL查询
    @Query(value = "SELECT count(*) FROM t_person", nativeQuery = true)
    long getTotalRow();

}
