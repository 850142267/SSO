package com.stjh.ssodemo.constants;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author KangJian
 * @date 2018/7/18
 */
public enum ResponseCode {
	/**
	 * 成功
	 */
	SUCCESS(200),
	/**
	 * 失败
	 */
	FAIL(400),
	/**
	 * 未登录
	 */
	UNAUTHORIZED(401),
	/**
	 * 未授权
	 */
	FORBIDDEN(403),
	/**
	 * 接口不存在
	 */
	NOT_FOUND(404),
	/**
	 * 无法预览
	 **/
	NOT_ACCEPTABLE(406),
	/**
	 * 服务器内部错误
	 */
	INTERNAL_SERVER_ERROR(500);

	/**
	 * 状态码
	 */
	private final int code;

	ResponseCode(int code) {
		this.code = code;
	}

	/**
	 * 状态码
	 * @return
	 */
	public int code() {
		return code;
	}
}
