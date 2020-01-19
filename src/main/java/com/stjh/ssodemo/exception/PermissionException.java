package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * @author luoxiaowen
 * @Date 2018/9/4
 */
public class PermissionException extends BaseException {
	public PermissionException() {
	}

	public PermissionException(String message) {
		super(message);
	}

	public PermissionException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * 获取异常状态码
	 * @return
	 */
	@Override
	protected HttpCode getCode() {
		return HttpCode.FORBIDDEN;
	}
}
