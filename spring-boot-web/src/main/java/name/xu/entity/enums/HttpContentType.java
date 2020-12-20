package name.xu.entity.enums;

/**
 * Created by huoxu
 */
public enum HttpContentType {
    /**
     * 默认类型,以键值对的形式提交给后台,servlet中可以使用request.getParameter()获取到值
     */
    DEFAULT_TYPE(0, "application/x-www-form-urlencoded"),
    /**
     * json类型,用流接收
     */
    APPLICATION_JSON(1, "application/json"),
    /**
     * 纯文本类型,用流接收
     */
    TEXT_PLAIN(1, "text/plain");

    private final Integer code;
    private final String value;

    HttpContentType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * @param code 值
     * @return value 值
     */
    public static String getValue(Integer code) {
        for (HttpContentType ele : values()) {
            if (ele.getCode() == code) {
                return ele.getValue();
            }
        }
        return null;
    }

    /**
     * @param  value 值
     * @return code 值
     */
    public static Integer getCode(String value) {
        for (HttpContentType ele : values()) {
            if (ele.getValue().equals(value)) {
                return ele.getCode();
            }
        }
        return null;
    }

    /**
     * 通过 code 获取枚举对象
     *
     * @param code 值
     * @return 枚举对象
     */
    public static HttpContentType parse(Integer code) {
        for (HttpContentType ele : values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
