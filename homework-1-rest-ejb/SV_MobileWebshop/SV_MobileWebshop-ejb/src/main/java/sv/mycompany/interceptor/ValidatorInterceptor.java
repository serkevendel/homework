package sv.mycompany.interceptor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;

import sv.mycompany.annotation.Validate;
import sv.mycompany.annotation.ValidatorQualifier;
import sv.mycompany.exception.ValidationException;
import javax.inject.Inject;
import javax.validation.Validator;


@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject @ValidatorQualifier
    private Validator validator;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        Arrays.asList(parameters).stream().filter(p -> p.getClass().isAnnotationPresent(Validate.class)).forEach(p -> validateBean(p));
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage()  + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}
