package demo.bfims.Config.GlobalExceptionHandler;

import demo.bfims.Config.Response;
import demo.bfims.Enums.ResponseType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        System.out.println("\nDataIntegrityViolation:\n" + e);
        Response response = new Response();
        response.getMessages().put(ResponseType.ERROR.toString(), "Key Constraint Violation, parent row cannot be removed with children.");
        response.getMessages().put(ResponseType.MESSAGE.toString(),e.getMessage());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(409));
    }
}
