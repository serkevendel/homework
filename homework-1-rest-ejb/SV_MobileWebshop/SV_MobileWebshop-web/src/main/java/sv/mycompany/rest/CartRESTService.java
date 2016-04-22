package sv.mycompany.rest;

import sv.mycompany.dto.MobileDTO;
import sv.mycompany.dto.UserDTO;
import sv.mycompany.exception.BadRequestException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sv.mycompany.service.CartService;
import sv.mycompany.service.UserManagementService;

@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class CartRESTService implements Serializable {

    @Inject
    private transient CartService cartService;

    @Inject
    private transient UserManagementService userManagementService;

    @GET
    @Path("/")
    public List<MobileDTO> viewCart(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        for (UserDTO user : userManagementService.getUsers()) {
            if (null != session.getAttribute(user.getUsername())) {
                return cartService.getCart();
            }
        }

        throw new BadRequestException("Login required for this operation.");
    }

    @POST
    @Path("/")
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {

        HttpSession session = request.getSession(true);
        for (UserDTO user : userManagementService.getUsers()) {
            if (null != session.getAttribute(user.getUsername())) {
                return cartService.addToCart(mobile);
            }
        }

        throw new BadRequestException("Login required for this operation.");
    }

    @POST
    @Path("/checkout")
    public String checkout(@Context HttpServletRequest request) {
        if (null != request.getSession(false)) {
            cartService.checkout();
            request.getSession().invalidate();
            return "Checkout successful.";
        } else {
            throw new BadRequestException("Login required for this operation.");
        }
    }
}
