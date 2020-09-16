package ir.shayandaneshvar.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SpringBlogException extends RuntimeException {
    public SpringBlogException() {
        super();
    }

    public SpringBlogException(String message) {
        super(message);
    }

    public SpringBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringBlogException(Throwable cause) {
        super(cause);
    }
}
