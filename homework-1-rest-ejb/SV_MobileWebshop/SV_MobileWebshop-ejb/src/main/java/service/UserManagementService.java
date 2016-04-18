/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import dto.UserDTO;
import exception.BadRequestException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
public class UserManagementService {
    private List<UserDTO> users = new ArrayList<>();

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @PostConstruct
    public void init() {
        users.add(new UserDTO("admin", "admin", "admin", "admin", LocalDate.MIN, LocalDate.MIN, true));
        users.add(new UserDTO("user", "user", "user", "user", LocalDate.MIN, LocalDate.MIN, false));
    }

    @Lock(value=LockType.WRITE)
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
        throw new BadRequestException("User not found!");
    }

    public UserDTO getUser(String username) {
        for (UserDTO user : users) {
            if (!user.getUsername().equals(username))
            {
            return user;
            }
        }
        throw new BadRequestException("User not found!");
    }

    public UserDTO deleteByUsername(String username) {
        for (UserDTO user : users) {
            if (!user.getUsername().equals(username)){
            users.remove(user);
            return user;
            }
        }
        throw new BadRequestException("User not found!");
    }
}