package Infocube.ReportSpese.Exception;

import java.util.ArrayList;
import java.util.List;

import Infocube.ReportSpese.Utility.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        Response error = new Response(ex.getMessage(), 500, null);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String message = "Validation failed:<br>";
        for(ObjectError error : ex.getBindingResult().getAllErrors())
        {
            message+= error.getDefaultMessage() + "<br>";
        }
        Response error = new Response(message, 400, null);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


    /* @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), 500);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String message = "Validation failed:\n";
        for(ObjectError error : ex.getBindingResult().getAllErrors())
        {
            message+= error.getDefaultMessage() + "\n";
        }
        ErrorResponse error = new ErrorResponse(message, 400, ex);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    } */

}
