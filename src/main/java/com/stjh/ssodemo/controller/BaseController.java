package com.stjh.ssodemo.controller;

import com.stjh.ssodemo.constants.ResponseCode;
import com.stjh.ssodemo.entity.ResponseEntity;
import com.stjh.ssodemo.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * 全局基础controller
 *
 * @author KangJian
 * @date 2018/07/19
 */
public class BaseController {

	/**
	 * 默认返回信息
	 */
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	/**
	 * 成功返回
	 * @return
	 */
	public ResponseEntity successResult() {
		return new ResponseEntity().setCode(ResponseCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 成功返回
	 * @param data
	 * @param <T>
	 * @return
	 */
	public <T> ResponseEntity<T> successResult(T data) {
		return new ResponseEntity().setCode(ResponseCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
	}

	/**
	 * 失败返回
	 * @param message
	 * @return
	 */
	public ResponseEntity failResult(String message) {
		return new ResponseEntity().setCode(ResponseCode.FAIL).setMessage(message);
	}

	/**
	 * 入参校验
	 * @param result
	 * @throws ServiceException
	 */
	protected void checkBindingResult(BindingResult result) throws ServiceException {
		if (result != null && result.getErrorCount() > 0) {
			String mess = "";
			for (ObjectError oe : result.getAllErrors()) {
				if (oe instanceof FieldError) {
					mess = mess + oe.getDefaultMessage() + ";";
				} else {
					mess = mess + oe.getDefaultMessage();
				}
			}
			if (mess.endsWith(";")) {
				mess = mess.substring(0, mess.length() - 1);
				throw new ServiceException(mess);
			}
		}
	}
}
