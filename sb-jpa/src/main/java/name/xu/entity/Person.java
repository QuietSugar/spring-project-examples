package name.xu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Created by HuoXu
 */
@Data
@Entity
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "varchar(128)  comment '名称'")
    private String name;
    /**
     * 年龄
     */
    @Column(name = "age", columnDefinition = "int comment '年龄'")
    private int age;
    /**
     * 电子邮件
     */
    @Column(name = "email", columnDefinition = "varchar(128)  comment '电子邮件'")
    private String email;
    /**
     * 地址
     */
    @Column(name = "address", columnDefinition = "varchar(128)  comment '地址'")
    private String address;
}
