package sv.mycompany.rest;

import sv.mycompany.dto.MobileDTO;
import sv.mycompany.exception.BadRequestException;
import sv.mycompany.interceptor.BeanValidation;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import static sv.mycompany.rest.UserRESTService.sessionUser;
import sv.mycompany.service.InventoryService;
import sv.mycompany.service.UserManagementService;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryRESTService {

    @Inject
    private InventoryService inventoryService;

    @Inject
    private UserManagementService userManagementService;

    @POST
    @Path("/")
    @BeanValidation
    public MobileDTO addMobile(@Context HttpServletRequest request, MobileDTO mobile) {

        if (null != request.getSession().getAttribute(sessionUser) && userManagementService.getUser(sessionUser).isAdmin()) {
            return inventoryService.addMobile(mobile);
        }
        throw new BadRequestException("Admin login is required for this operation.");
    }

    @GET
    @Path("/")
    public List<MobileDTO> getMobiles() {
        return inventoryService.getMobilesList();
    }
}
