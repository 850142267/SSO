package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * 全局业务异常类
 *
 * @author KangJian
 * @date 2018/7/18
 */
public class ServiceException extends BaseException {
	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	protected HttpCode getCode() {
		return HttpCode.INTERNAL_SERVER_ERROR;
	}
}
