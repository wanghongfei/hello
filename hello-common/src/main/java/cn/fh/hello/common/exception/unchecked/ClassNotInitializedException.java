package cn.fh.hello.common.exception.unchecked;

public class ClassNotInitializedException extends RuntimeException {
	public ClassNotInitializedException(String msg) {
		super(msg);
	}
}
