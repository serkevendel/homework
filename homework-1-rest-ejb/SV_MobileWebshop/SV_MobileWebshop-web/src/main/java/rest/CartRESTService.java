/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import dto.MobileDTO;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import service.CartService;

/**
 *
 * @author Serke Vendel
 */
@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartRESTService {
    
    @Inject
    private CartService cartService;
    
    @POST
    public List<MobileDTO> addToCart(MobileDTO mobile){
       return cartService.addToCart(mobile);
    }
            
    @POST
    @Path("/checkout")
    public void checkout(@Context HttpServletRequest request) {
        cartService.checkout();
        request.getSession().invalidate();
    }
}
