package name.xu.vo;


public class ResultUtil {

	public static Result<?> getResult(ResultEnum re) {
		return new Result(re.getCode(), re.getMsg());
	}

	public static Result<?> getResult(ResultEnum re, String msg) {
		return new Result(re.getCode(), msg);
	}

}
