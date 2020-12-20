package name.xu.vo;

public enum ResultEnum {

	SUCCESS(200, "OK"),
	DATA_EXIST(201, "data exist"),

	SIGN_ERROR(400,"sign error"),
	NOT_LOG_IN(401,"not logged in"),
    INVALID_USER(402,"invalid username or password"),
	RESOURCE_FORBIDDEN(403,"resource forbidden"),
	RESOURCE_NOT_FOUND(404,"resource not found"),
	METHOD_NOT_SUPPORTED(405, "method not supported"),
    PARAM_ERROR(406,"param error"),
	MIMETYPE_NOT_SUPPORTED(415, "mimetype not supported"),

	SERVER_ERROR(500, "error"),
	;

	private int code;
	private String msg;

	ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
