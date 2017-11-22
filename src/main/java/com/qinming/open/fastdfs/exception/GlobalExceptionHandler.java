package com.qinming.open.fastdfs.exception;

import com.qinming.open.fastdfs.domain.ReturnCode;
import com.qinming.open.fastdfs.domain.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by QinMing on 2017/10/26.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnMessage exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error(req.getRemoteAddr() + ", " + req.getRequestURI() + ", " + e.getMessage(), e);
        if (e instanceof AppServiceException) {
            return new ReturnMessage(((AppServiceException) e).getReturnCode());
        }
        return new ReturnMessage(ReturnCode.SERVER_ERROR);
    }
}
