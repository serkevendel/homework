
package REST;

import ejb.AsyncEJB;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vendel
 */
@Path("/async")
@SessionScoped
@Produces(MediaType.TEXT_PLAIN)
public class AsyncREST implements Serializable{
    
    @Inject
    private AsyncEJB asyncEJB;
    
    @GET
    public String testAsync(@Context HttpServletRequest request) throws InterruptedException, ExecutionException{
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1000);
        Future<Integer> i1 = asyncEJB.longTimeOperation();
        Future<Integer> i2 = asyncEJB.longTimeOperation();
        i2.cancel(true);
        Integer cancelledNum = i2.get();
        Integer runned = i1.get();
        return "Cancelled: "+ cancelledNum.toString() + "Runned: " + runned.toString();
    }
}
