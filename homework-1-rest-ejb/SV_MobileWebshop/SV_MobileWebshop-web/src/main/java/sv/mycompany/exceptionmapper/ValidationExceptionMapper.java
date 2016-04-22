package sv.mycompany.exceptionmapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import sv.mycompany.exception.ErrorDTO;
import sv.mycompany.exception.ValidationException;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Inject
    private Logger LOGGER;
    
    @Override
    public Response toResponse(ValidationException exception) {
        LOGGER.log(Level.WARNING,exception.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
