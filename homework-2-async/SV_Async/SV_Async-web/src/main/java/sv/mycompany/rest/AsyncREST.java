package sv.mycompany.rest;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sv.mycompany.service.InstantRespondingService;
import sv.mycompany.service.LongRespondingService;

/**
 *
 * @author Vendel
 */
@Path("/async")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@SessionScoped
public class AsyncREST implements Serializable {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Inject
    private InstantRespondingService instantRespondingService;

    @Inject
    private LongRespondingService longRespondingService;

    @Path("/instant")
    @POST
    public void requestInstant(@Context HttpServletRequest request, String text) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);
        String result = instantRespondingService.instantMethod(text);
        LOGGER.info(result);
    }

    @Path("/long")
    @POST
    public void requestLongandInstant(@Context HttpServletRequest request, String seconds) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);
        Future<String> result = longRespondingService.longRespondingTask(Integer.parseInt(seconds));
        LOGGER.info("Long process started");
        LOGGER.info(instantRespondingService.instantMethod("other operation between start and end performed"));
        result.get();
        if (result.isDone()) {
            LOGGER.info("Long process finished");
        }

    }

    @Path("/longandcancel")
    @POST
    public void requestAndCancel(@Context HttpServletRequest request, String seconds) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);
        Future<String> cancelled = longRespondingService.longRespondingTask(Integer.parseInt(seconds));
        cancelled.cancel(true);
        LOGGER.info(cancelled.get());
    }
}
