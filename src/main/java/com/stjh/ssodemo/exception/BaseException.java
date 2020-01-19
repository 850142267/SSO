package com.stjh.ssodemo.exception;

import com.stjh.ssodemo.constants.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

/**
 * 全局基础异常
 *
 * @author KangJian
 * @date 2018/7/19
 */
public abstract class BaseException extends RuntimeException {
	public BaseException() {
	}

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * 设置异常信息
	 * @param modelMap
	 */
	public void handler(ModelMap modelMap) {
		modelMap.put("code", getCode().value());
		if (StringUtils.isNotBlank(getMessage())) {
			modelMap.put("msg", getMessage());
		} else {
			modelMap.put("msg", getCode().msg());
		}
		modelMap.put("timestamp", System.currentTimeMillis());
	}

	/**
	 * 获取异常状态码
	 * @return
	 */
	protected abstract HttpCode getCode();
}
