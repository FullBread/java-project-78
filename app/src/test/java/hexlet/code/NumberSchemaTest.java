package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    public void testNullIsValid() {
        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();
        boolean isValid = numberSchema.isValid(null);
        Assertions.assertTrue(isValid);
        NumberSchema schemaWithPositive = validator.number().positive();
        assertTrue(schemaWithPositive.isValid(null));
    }
    @Test
    public void testRequiredPositive() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number().required().positive();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(1));
    }

    @Test
    public void testRequiredPositiveInRange() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number().required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));

        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(6));
    }
}
