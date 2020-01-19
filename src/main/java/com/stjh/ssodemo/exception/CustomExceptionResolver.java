package com.stjh.ssodemo.exception;

import com.alibaba.fastjson.JSON;
import com.stjh.ssodemo.constants.ResponseCode;
import com.stjh.ssodemo.entity.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author KangJian
 * @date 2018/07/19
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
	/**
	 * 日志
	 */
	private final Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception e) {
		ResponseEntity result = new ResponseEntity();
		if (e instanceof ServiceException) {
			//业务失败的异常
			result.setCode(ResponseCode.FAIL).setMessage(e.getMessage());
			logger.error(e.getMessage());
		} else if (e instanceof LoginException) {
			result.setCode(ResponseCode.UNAUTHORIZED).setMessage(e.getMessage());
			logger.info(e.getMessage());
		} else if (e instanceof PermissionException) {
			result.setCode(ResponseCode.FORBIDDEN).setMessage(e.getMessage());
			logger.info(e.getMessage());
		} else if (e instanceof PreviewException) {
			result.setCode(ResponseCode.NOT_ACCEPTABLE).setMessage(e.getMessage());
			logger.info(e.getMessage());
		} else if (e instanceof NoHandlerFoundException) {
			result.setCode(ResponseCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
			logger.error(e.getMessage());
		} else if (e instanceof ServletException) {
			result.setCode(ResponseCode.FAIL).setMessage(e.getMessage());
			logger.error(e.getMessage());
		} else {
			result.setCode(ResponseCode.INTERNAL_SERVER_ERROR)
					.setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
			String message;
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
						handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(),
						e.getMessage());
			} else {
				message = e.getMessage();
			}
			logger.error(message, e);
		}
		responseResult(response, result);
		return new ModelAndView();
	}

	/**
	 * 生成返回信息
	 * @param response
	 * @param result
	 */
	private void responseResult(HttpServletResponse response, ResponseEntity result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(200);
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}
}
