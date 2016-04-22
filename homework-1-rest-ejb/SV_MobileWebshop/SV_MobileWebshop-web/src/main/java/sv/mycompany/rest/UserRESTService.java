package sv.mycompany.rest;

import sv.mycompany.dto.UserDTO;
import sv.mycompany.exception.BadRequestException;
import sv.mycompany.interceptor.BeanValidation;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sv.mycompany.service.UserManagementService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService {

    public static String sessionUser;
    @Inject
    private UserManagementService userManagementService;

    @POST
    @Path("/")
    public UserDTO addUser(UserDTO user) {
        userManagementService.addUser(user);
        return user;
    }

    @DELETE
    @Path("/{username}")
    public UserDTO removeUser(@PathParam(value = "username") String username) {
        return userManagementService.deleteByUsername(username);
    }

    @PUT
    @Path("/{username}")
    @BeanValidation
    public UserDTO editUser(@PathParam(value = "username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new BadRequestException("JSON username points to wrong url");
        } else {
            userManagementService.editUser(user);
            return userManagementService.getUser(user.getUsername());
        }
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam(value = "username") String username) {
        return userManagementService.getUser(username);
    }

    @GET
    @Path("/")
    public List<UserDTO> getUsers() {
        return userManagementService.getUsers();
    }

    @POST
    @Path("/login")
    public UserDTO login(@Context HttpServletRequest request,
            @QueryParam(value = "username") String username,
            @QueryParam(value = "password") String password) {
        for (UserDTO user : userManagementService.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                if (null != session.getAttribute(username)) {
                    session.invalidate();
                    throw new BadRequestException("User is already logged in!");
                }
                sessionUser = user.getUsername();
                session.setAttribute(sessionUser, user);
                session.setMaxInactiveInterval(4000);
                return user;
            }
        }
        throw new BadRequestException("Username or Password is incorrect!");
    }

    @POST
    @Path("/logout")
    public String logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "Logout successful";
    }
}
