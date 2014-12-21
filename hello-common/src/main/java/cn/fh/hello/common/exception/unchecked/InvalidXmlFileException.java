package cn.fh.hello.common.exception.unchecked;

public class InvalidXmlFileException extends RuntimeException {
	public InvalidXmlFileException(String msg) {
		super(msg);
	}
}
