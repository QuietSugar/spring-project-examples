package name.xu.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Created by HuoXu
 */
@Data
@Entity
@Table(name = "user")
public class User {
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
     * uuid
     * 唯一标识,不可重复
     */
    @Column(name = "uuid", columnDefinition = "varchar(128) unique  comment '唯一标识'")
    private String uuid;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`", nullable = false, columnDefinition = " datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间'")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`", nullable = false, columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
