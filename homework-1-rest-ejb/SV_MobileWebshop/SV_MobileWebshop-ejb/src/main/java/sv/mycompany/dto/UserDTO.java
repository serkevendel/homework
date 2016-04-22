
package sv.mycompany.dto;

import sv.mycompany.annotation.Validate;
import sv.mycompany.constraint.BirthDateConstraint;
import sv.mycompany.interceptor.BeanValidation;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validate
@BirthDateConstraint
@BeanValidation
public class UserDTO implements Serializable{
    @NotNull
    @Pattern(regexp="....*")
    private String username;
    @NotNull
    @Pattern(regexp="^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[= + < > . ,]).*$")
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    @NotNull
    private LocalDate registrationDate;
    private boolean admin;

    public UserDTO(String username, String password, String firstname, String lastname, LocalDate dateOfBirth, LocalDate registrationDate, boolean admin) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.admin = admin;
    }

    public UserDTO() {
        //Empty public constructor for UserDTO
    }
    

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + Objects.hashCode(this.password);
        hash = 11 * hash + Objects.hashCode(this.firstname);
        hash = 11 * hash + Objects.hashCode(this.lastname);
        hash = 11 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 11 * hash + Objects.hashCode(this.registrationDate);
        hash = 11 * hash + (this.admin ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.admin != other.admin) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.registrationDate, other.registrationDate)) {
            return false;
        }
        return true;
    }

}