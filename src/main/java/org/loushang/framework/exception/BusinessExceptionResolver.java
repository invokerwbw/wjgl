package org.loushang.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 覆盖loushang-framework中的异常解析器
 * <p>
 * 目的是
 * <ul>
 * <li>使用slf4j在控制台输出异常信息</li>
 * </ul>
 *
 * @author wbw
 * @since 2016年9月23日 下午1:55:29
 */
public class BusinessExceptionResolver extends SimpleMappingExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		this.logger.error("异常信息", ex);
		String viewName = determineViewName(ex, request);

		if (viewName != null) {
			if ((request.getHeader("accept").indexOf("application/json") <= -1)
					&& ((request.getHeader("X-Requested-With") == null)
							|| (request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") <= -1))) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode.intValue());
				}
				return getModelAndView(viewName, ex, request);
			}
			try {
				response.setContentType("text/json;charset=UTF-8");
				if (ex.getMessage() != null)
					response.getWriter().print(ex.getMessage());
				else {
					response.getWriter().print("系统异常");
				}
				response.getWriter().close();
			} catch (Exception e) {
				this.logger.error("异常信息", e);
			}
			return null;
		}

		return null;
	}
}