package constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface BirthDateConstraint {

    String message() default "{FirstnameLastname.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
