
package rest;

import dto.MobileDTO;
import dto.UserDTO;
import exception.BadRequestException;
import interceptor.BeanValidation;
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
import service.InventoryService;
import service.UserManagementService;


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
    public MobileDTO addMobile(@Context HttpServletRequest request,MobileDTO mobile){
        
            for (UserDTO user : userManagementService.getUsers()) {
               if(null != request.getSession().getAttribute(user.getUsername()) && user.isAdmin()){
                   return inventoryService.addMobile(mobile);
               }
            }
            throw new BadRequestException("Admin login required for this operation.");
    }
    
    @GET
    @Path("/")
    public List<MobileDTO> getMobiles(){
        return inventoryService.getMobilesList();
    }
}
