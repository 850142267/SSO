package com.stjh.ssodemo.exception;


import com.stjh.ssodemo.constants.HttpCode;

/**
 * @author luoxiaowen
 * @Date 2018/9/20
 */
public class PreviewException extends BaseException {
	public PreviewException() {
	}

	public PreviewException(String message) {
		super(message);
	}

	public PreviewException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * 获取异常状态码
	 * @return
	 */
	@Override
	protected HttpCode getCode() {
		return HttpCode.NOT_ACCEPTABLE;
	}
}
