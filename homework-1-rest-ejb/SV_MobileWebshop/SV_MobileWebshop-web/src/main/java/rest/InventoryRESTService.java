/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.MobileDTO;
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

/**
 *
 * @author Serke Vendel
 */
@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryRESTService {
    
    @Inject
    private InventoryService inventoryService;
    
    @POST
    @Path("/")
    @BeanValidation
    public MobileDTO addMobile(@Context HttpServletRequest request,MobileDTO mobile){
        if(null != request.getSession().getAttribute("admin")) { 
            return inventoryService.addMobile(mobile);
        }
        else{
            throw new BadRequestException("Access denied, operation is admin only!");
        }
    }
    
    @GET
    @Path("/")
    public List<MobileDTO> getMobiles(){
        return inventoryService.getMobilesList();
    }
}
