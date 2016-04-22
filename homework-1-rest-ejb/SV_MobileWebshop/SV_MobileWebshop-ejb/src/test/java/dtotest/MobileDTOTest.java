
package dtotest;

import dto.MobileDTO;
import java.util.ArrayList;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MobileDTOTest {
    
    private MobileDTO testmobile;
    
    @Before
    public void initTestUser(){
       testmobile = new MobileDTO("Testtype", "Testmanufacturer", 2000, 10);
    }
    
    
    @Test
    public void IdTest_Pass(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0,violations.size());
    }
    
    @Test
    public void IdTest_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setId("invalid");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals("invalid",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void TypeTest_Pass(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0,violations.size());
    }
    
    @Test
    public void TypeTestNotNull_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setType(null);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }
    
    @Test
    public void TypeTestPattern_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setType("2c");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals("2c",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void ManufacturerTest_Pass(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0,violations.size());
    }
    
    @Test
    public void ManufacturerTestNotNull_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setManufacturer(null);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }
    
    @Test
    public void ManufacturerTestPattern_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setManufacturer("2c");
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals("2c",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void PriceTest_Pass(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0,violations.size());
    }
    
    @Test
    public void PriceTest_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setPrice(0);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals(0,violations.get(0).getInvalidValue());
    }
    
    @Test
    public void PieceTest_Pass(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(0,violations.size());
    }
    
    @Test
    public void PieceTest_Violation(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        testmobile.setPiece(-1);
        ArrayList<ConstraintViolation<MobileDTO>> violations = new ArrayList(validator.validate(testmobile));
        Assert.assertEquals(1,violations.size());
        Assert.assertEquals(-1,violations.get(0).getInvalidValue());
    }
    
            
}
