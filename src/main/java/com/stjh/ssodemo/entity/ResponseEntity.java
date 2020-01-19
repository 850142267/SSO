package com.stjh.ssodemo.entity;


import com.alibaba.fastjson.JSON;
import com.stjh.ssodemo.constants.ResponseCode;

/**
 * 统一API响应结果封装
 *
 * @author KangJian
 * @date 2018/7/18
 */
public class ResponseEntity<T> {
	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 返回数据体
	 */
	private T data;

	/**
	 * 设置状态码
	 * @param responseCode
	 * @return
	 */
	public ResponseEntity setCode(ResponseCode responseCode) {
		this.code = responseCode.code();
		return this;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 设置返回信息
	 * @param message
	 * @return
	 */
	public ResponseEntity setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	/**
	 * 设置返回数据体
	 * @param data
	 * @return
	 */
	public ResponseEntity setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
