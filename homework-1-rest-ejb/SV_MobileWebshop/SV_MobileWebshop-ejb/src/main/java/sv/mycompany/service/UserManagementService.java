package sv.mycompany.service;

import sv.mycompany.dto.UserDTO;
import sv.mycompany.exception.BadRequestException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class UserManagementService implements Serializable {
    private static final String EXC_MESSAGE = "User not found!";
    private final List<UserDTO> users = new ArrayList<>();

    public List<UserDTO> getUsers() {
        return users;
    }

    @PostConstruct
    public void init() {
        users.add(new UserDTO("admin", "admin", "admin", "admin", LocalDate.MIN, LocalDate.now(), true));
        users.add(new UserDTO("user", "user", "user", "user", LocalDate.MIN, LocalDate.now(), false));
    }

    @Lock(value = LockType.WRITE)
    public List<UserDTO> addUser(UserDTO user) {
        if (!users.contains(user)) {
            users.add(user);
            return users;
        }
        throw new BadRequestException("User is already added!");
    }

    public List<UserDTO> removeUser(UserDTO user) {
        users.remove(user);
        return users;
    }

    public List<UserDTO> editUser(UserDTO user) {
        if (null != deleteByUsername(user.getUsername())) {
            users.add(user);
            return users;
        }
        throw new BadRequestException(EXC_MESSAGE);
    }

    public UserDTO getUser(String username) {
        for (UserDTO user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new BadRequestException(EXC_MESSAGE);
    }

    public UserDTO deleteByUsername(String username) {
        for (UserDTO user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                return user;
            }
        }
        throw new BadRequestException(EXC_MESSAGE);
    }
}
