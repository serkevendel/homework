
package rest;


import dto.UserDTO;
import exception.BadRequestException;
import interceptor.BeanValidation;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import service.UserManagementService;

/**
 *
 * @author Serke Vendel
 */
@Stateless
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService {
    
    @Inject
    private UserManagementService userManagementService;
    
    @POST
    public UserDTO addUser (UserDTO user){
        userManagementService.addUser(user);
        return user;
    }
    
    @DELETE
    @Path("/{username}")
    public UserDTO removeUser (@PathParam(value = "username") String username){
        return userManagementService.deleteByUsername(username);
    }
    
    @PUT
    @Path("/{username}")
    @BeanValidation
    public UserDTO editUser (@PathParam (value = "username") String username, UserDTO user){
       if(!user.getUsername().equals(username)){
           throw new BadRequestException ("JSON username points to wrong url");
       }
       else{
            userManagementService.editUser(user);
            return userManagementService.getUser(user.getUsername());
       }
    }
    
    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam (value = "username") String username){
        return userManagementService.getUser(username);
    }
    
    @GET
    public List<UserDTO> getUsers(){
        return userManagementService.getUsers();
    }
    
    @POST
    @Path("/login")
    public UserDTO login(@Context HttpServletRequest request, String username, String password){
        for (UserDTO user : userManagementService.getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute(user.getUsername(), user);
                session.setMaxInactiveInterval(1800);
                return user;
            } 
        }
        throw new BadRequestException("Username or Password is incorrect!");
    }
    
    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
    }
}
