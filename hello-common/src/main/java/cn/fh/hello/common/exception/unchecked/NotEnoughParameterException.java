package cn.fh.hello.common.exception.unchecked;

public class NotEnoughParameterException extends RuntimeException {
	public NotEnoughParameterException(String msg) {
		super(msg);
	}
}
