package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * @author luoxiaowen
 * @Date 2018/9/4
 */
public class LoginException extends BaseException {

	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * 获取异常状态码
	 * @return
	 */
	@Override
	protected HttpCode getCode() {
		return HttpCode.UNAUTHORIZED;
	}
}
