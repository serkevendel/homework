package sv.mycompany.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.mycompany.bean.StatisticsBean;

/**
 *
 * @author Vendel
 */
@Path("/jobresult")
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private StatisticsBean statisticsBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJobResults() {
        return statisticsBean.getResults().toString();
    }

}
