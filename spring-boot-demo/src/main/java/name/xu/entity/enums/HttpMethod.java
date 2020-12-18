package name.xu.entity.enums;

/**
 * Created by huoxu
 */
public enum HttpMethod {
    /**
     * POST
     */
    POST("POST"),
    /**
     * GET
     */
    GET("GET");


    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
