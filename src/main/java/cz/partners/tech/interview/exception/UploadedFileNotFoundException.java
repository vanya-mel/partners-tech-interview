package cz.partners.tech.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UploadedFileNotFoundException extends RuntimeException {

    public UploadedFileNotFoundException(String message) {
        super(message);
    }

}
