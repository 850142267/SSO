package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * 实例化异常
 *
 * @author KangJian
 * @date 2018/7/19
 */
public class InstanceException extends BaseException {
	public InstanceException() {
		super();
	}

	public InstanceException(Throwable t) {
		super(t);
	}

	@Override
	protected HttpCode getCode() {
		return HttpCode.INTERNAL_SERVER_ERROR;
	}
}
