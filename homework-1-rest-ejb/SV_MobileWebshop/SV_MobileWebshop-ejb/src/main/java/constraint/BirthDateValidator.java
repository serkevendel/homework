package constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import dto.UserDTO;
import java.time.LocalDate;


public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, UserDTO> {

    @Override
    public void initialize(BirthDateConstraint constraintAnnotation) {
        //initializing
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if (user.getRegistrationDate()== null){
            return false;
        }
        return user.getDateOfBirth().isBefore(user.getRegistrationDate()) && user.getDateOfBirth().isBefore(LocalDate.now());
}
}
