package sv.mycompany.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import sv.mycompany.dto.UserDTO;
import java.time.LocalDate;


public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, UserDTO> {

    @Override
    public void initialize(BirthDateConstraint constraintAnnotation) {
        //initializing
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if (null==user.getDateOfBirth()){
            return true;
        }
        return user.getDateOfBirth().isBefore(user.getRegistrationDate()) && user.getDateOfBirth().isBefore(LocalDate.now());
}
}
