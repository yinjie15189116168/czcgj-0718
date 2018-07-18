package com.sbq.ws.scan;

import com.sbq.common.response.server_error.ServerErrorSReponse;
import com.sbq.tools.BeanUtils;
import com.sbq.tools.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理，对ajax类型的异常返回ajax错误，避免页面问题
 * Created by L.cm
 * Date: 2016-24-03 16:18
 */
@Component
@SuppressWarnings("unchecked")
public class ExceptionResolver implements HandlerExceptionResolver {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
        // log记录异常
        logger.error(e.getMessage(), e);
        // 非控制器请求照成的异常
        if (!(handler instanceof HandlerMethod)) {
            return new ModelAndView("error/500");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (WebUtil.isAjax(handlerMethod)) {

            ServerErrorSReponse result = new ServerErrorSReponse();
            result.setDescription(e.getMessage());

            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setObjectMapper(jacksonObjectMapper);
            view.setContentType("text/html;charset=UTF-8");
            return new ModelAndView(view, BeanUtils.toMap(result));
        }

        // 页面指定状态为500，便于上层的resion或者nginx的500页面跳转，由于error/500不适合对用户展示
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView("error/500").addObject("error", e.getMessage());
    }

}