package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * 数据转换异常
 *
 * @author KangJian
 * @date 2018/7/19
 */
public class DataParseException extends BaseException {

	public DataParseException() {
	}

	public DataParseException(Throwable ex) {
		super(ex);
	}

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Throwable ex) {
		super(message, ex);
	}

	@Override
	protected HttpCode getCode() {
		return HttpCode.INTERNAL_SERVER_ERROR;
	}

}
