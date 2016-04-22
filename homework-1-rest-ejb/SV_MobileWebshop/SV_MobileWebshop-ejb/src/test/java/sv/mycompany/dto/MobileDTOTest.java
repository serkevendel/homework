package sv.mycompany.dto;

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

public class MobileDTOTest {

    private MobileDTO testmobile;
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
        testmobile = new MobileDTO("Testtype", "Testmanufacturer", 2000, 10);
    }

    @Test
    public void IdTest_Pass() {
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void IdTest_Violation() {
        testmobile.setId("invalid");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid", violations.get(0).getInvalidValue());
    }

    @Test
    public void TypeTest_Pass() {
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void TypeTestNotNull_Violation() {
        testmobile.setType(null);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
    }

    @Test
    public void TypeTestPattern_Violation() {
        testmobile.setType("2c");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("2c", violations.get(0).getInvalidValue());
    }

    @Test
    public void ManufacturerTest_Pass() {
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void ManufacturerTestNotNull_Violation() {
        testmobile.setManufacturer(null);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
    }

    @Test
    public void ManufacturerTestPattern_Violation() {
        testmobile.setManufacturer("2c");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("2c", violations.get(0).getInvalidValue());
    }

    @Test
    public void PriceTest_Pass() {
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void PriceTest_Violation() {
        testmobile.setPrice(0);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(0, violations.get(0).getInvalidValue());
    }

    @Test
    public void PieceTest_Pass() {
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void PieceTest_Violation() {
        testmobile.setPiece(-1);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(-1, violations.get(0).getInvalidValue());
    }

}
