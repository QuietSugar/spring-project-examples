package name.xu.entity;


import lombok.Data;

@Data
public class Area {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级编码
     */
    private String parentCode;
    /**
     * 级别
     */
    private int level;
}
