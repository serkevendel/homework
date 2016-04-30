package sv.mycompany.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException() {
        super();
    }

}
