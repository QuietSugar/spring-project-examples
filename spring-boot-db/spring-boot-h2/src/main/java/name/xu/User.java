package name.xu;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author huoxu
 */

@Entity
@Data
@Table(name = "dev_user")
public class User {
    /**
     * 主键
     */
    @Id
    private Integer id;
    /**
     * 名称
     */
    @Column
    private String name;
    /**
     * 年龄
     */
    @Column
    private Integer age;
    /**
     * 创建时间
     */
    @Column
    private Date createTime;

    /**
     * 更新时间
     */
    @Column
    private Date updateTime;
}
