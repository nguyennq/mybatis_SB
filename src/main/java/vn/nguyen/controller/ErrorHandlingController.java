package vn.nguyen.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.nguyen.Response.ErrorResponse;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.exception.CustomerNotFoundException;
import vn.nguyen.utils.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by nals on 1/26/18.
 */
@RestController
@Slf4j
@ControllerAdvice
public class ErrorHandlingController {

    private static final java.lang.String MESSAGE_DELIMITER = "-";

    @Autowired
    private MessageSource messageSource;

    public ErrorResponse processGlobalException(Exception ex, HttpServletRequest servletRequest){
        log.error(ex.getMessage(), ex);
        return createErrorResponse(StatusCode.ERROR_TECHNICAL);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest servletRequest){
        log.info(ex.getMessage(),ex);
        return createErrorResponse(StatusCode.ERROR_CUSTOMER_ID_NOT_FOUND, ex.getId());
    }

    @ExceptionHandler(CustomerIDExistException.class)
    public ErrorResponse handleCustomerIDExistException(CustomerIDExistException ex, HttpServletRequest servletRequest){
        log.info(ex.getMessage(),ex);
        return createErrorResponse(StatusCode.ERROR_CUSTOMER_ID_EXIST, ex.getId());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse processValidationError(HttpServletRequest request, HttpServletResponse response,
                                                Object handler, MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        if (isObjectValidateError(result)) {
            ObjectError error = result.getGlobalError();
            log.info(String.format("Handling validation error - Object [%s] - Error [%s]", error.getObjectName(), error.getDefaultMessage()), ex);
            return createErrorResponse(error.getDefaultMessage());
        } else {
            FieldError fieldError = result.getFieldErrors().get(0);
            String fieldName = fieldError.getField();
            String fieldMessage = fieldError.getDefaultMessage();
            String[] messageArgs = fieldMessage.split(MESSAGE_DELIMITER);
            String errorCode = messageArgs[0];
            messageArgs[0] = fieldName;
            log.info(String.format("Handling validation error - Field [%s] - Error [%s]", fieldError.getField(), fieldError.getDefaultMessage()), ex);
            return createErrorResponse(errorCode, messageArgs);
        }
    }

    private boolean isObjectValidateError(BindingResult result) {
        return result.getGlobalErrorCount() > 0;
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
