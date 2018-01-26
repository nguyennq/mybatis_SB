package vn.nguyen.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import vn.nguyen.Response.ErrorResponse;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.exception.CustomerNotFoundException;
import vn.nguyen.utils.StatusCode;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by nals on 1/26/18.
 */
@RestController
@Slf4j
@ControllerAdvice
public class ErrorHandlingController {

    @Autowired
    private MessageSource messageSource;

    public ErrorResponse procesGlobalException(Exception ex, HttpServletRequest servletRequest){
        log.error(ex.getMessage(), ex);
        return createErrorResponse(StatusCode.ERROR_TECHNICAL);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest servletRequest){
        log.error(ex.getMessage(),ex);
        return createErrorResponse(StatusCode.ERROR_CUSTOMER_ID_NOT_FOUND, ex.getId());
    }

    @ExceptionHandler(CustomerIDExistException.class)
    public ErrorResponse handleCustomerIDExistException(CustomerIDExistException ex, HttpServletRequest servletRequest){
        log.error(ex.getMessage(),ex);
        return createErrorResponse(StatusCode.ERROR_CUSTOMER_ID_EXIST, ex.getId());
    }


    private ErrorResponse createErrorResponse(String code, Object... o1) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessageCode(code);
        errorResponse.setMessageInfo(getMessage(code, o1));
        return errorResponse;
    }

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }

}
