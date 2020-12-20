package name.xu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
	private int code = 200;
	private String msg = "OK";
	private T result;

	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
