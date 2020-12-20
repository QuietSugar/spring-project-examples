package name.xu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 最常用数据类型
 * 只有 int,float,boolean,String
 *
 * @author Created by HuoXu
 */
@Data
@Entity
@Table(name = "basic_bean")
public class BasicBean {
    /**
     * 主键
     * 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "int_type", columnDefinition = "int  comment '整型'")
    private int intType;
    @Column(name = "float_type", columnDefinition = "float comment '浮点型'")
    private float floatType;
    @Column(name = "boolean_type", columnDefinition = "tinyint(1)  comment '布尔类型'")
    private boolean booleanType;
    @Column(name = "string_type", columnDefinition = "varchar(128)  comment '字符串'")
    private String stringType;

}
