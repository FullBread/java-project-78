package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void testOptionalString() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string()
                .minLength(0)
                .contains("");

        String optionalString = "";
        boolean isValid = stringSchema.isValid(optionalString);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testOptionalString2() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string()
                .minLength(0)
                .contains("");

        boolean isValid = stringSchema.isValid(null);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testValidString() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string()
                .required()
                .minLength(5)
                .contains("example");

        String validString = "This is an example string";
        boolean isValid = stringSchema.isValid(validString);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testInvalidString() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string()
                .required()
                .minLength(10)
                .contains("example");

        String invalidString = "Short";
        boolean isValid = stringSchema.isValid(invalidString);
        Assertions.assertFalse(isValid);
    }


    @Test
    public void testEmptyString() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();

        String optionalString = "";
        boolean isValid = stringSchema.isValid(optionalString);
        Assertions.assertTrue(isValid);
    }
}
