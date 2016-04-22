package sv.mycompany.userdtotest;

import sv.mycompany.dto.UserDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDTOTest {

    private UserDTO testuser;
    
    private static Validator validator;
    private static ValidatorFactory vf;

    @BeforeClass
    public static void initValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void closeValidatorFactory() {
        vf.close();
    }
    
    @Before
    public void initTestUser() {
        LocalDate birthDate = LocalDate.parse("1994-10-20");
        testuser = new UserDTO("testuser", "pW=1234", "testuser", "testuser", birthDate, LocalDate.now(), false);
    }

    @Test
    public void BirthDateValidatorTest_Violation() {
        testuser.setDateOfBirth(LocalDate.MAX);
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(1, violations.size());
        UserDTO resultUser = (UserDTO) violations.get(0).getInvalidValue();
        Assert.assertEquals(testuser.getDateOfBirth(), resultUser.getDateOfBirth());
    }

    @Test
    public void BirthDateValidatorTest_Pass() {
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void UsernameNotNullTest_Violation() {
        testuser.setUsername(null);
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
    }

    @Test
    public void UsernameNotNullTest_Pass() {
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void UsernamePatternTest_Violation() {
        testuser.setUsername("Na");
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Na", violations.get(0).getInvalidValue());
    }

    @Test
    public void UsernamePatternTest_Pass() {
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void PasswordPatternTest_Violation() {
        testuser.setPassword("password");
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("password", violations.get(0).getInvalidValue());
    }

    @Test
    public void PasswordNotNullTest_Pass() {
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void RegistrationDateNotNullTest_Pass() {
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void RegistrationDateNotNullTest_Violation() {
        testuser.setRegistrationDate(null);
        testuser.setDateOfBirth(null);
        ArrayList<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(testuser));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
    }
}
