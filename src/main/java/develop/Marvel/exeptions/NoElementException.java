package develop.Marvel.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoElementException extends RuntimeException{
    public NoElementException(String message) {
        super(message);

    }
}
