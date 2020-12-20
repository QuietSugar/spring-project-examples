package name.xu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Created by HuoXu
 */
@Data
@Entity
@Table(name = "flag")
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 标志
     */
    @Column(name = "flag", nullable = false, columnDefinition = "varchar(32)  comment '标志'")
    private String flag;

    /**
     * 批次号
     * 表示同一次操作插入的
     */
    @Column(name = "batch_code", nullable = false, columnDefinition = "varchar(128) comment '唯一标识'")
    private String batchCode;
}
