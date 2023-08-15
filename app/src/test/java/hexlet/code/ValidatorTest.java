package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(""));
        Assertions.assertFalse(schema.isValid(10));

        schema.required();
        Assertions.assertTrue(schema.isValid("string"));
        Assertions.assertFalse(schema.isValid(null));
        Assertions.assertFalse(schema.isValid(""));

        schema.minLength(5);
        Assertions.assertTrue(schema.isValid("This is string"));
        Assertions.assertFalse(schema.isValid("str"));
        Assertions.assertFalse(schema.isValid(null));

        schema.contains("is");
        Assertions.assertTrue(schema.isValid("This is an example string"));
        Assertions.assertFalse(schema.isValid("is"));
        Assertions.assertFalse(schema.isValid(null));


    }

    @Test
    public void testNumberSchema() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();
        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(1));
        Assertions.assertFalse(schema.isValid("string"));

        schema.required();
        Assertions.assertFalse(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(15));
        Assertions.assertTrue(schema.isValid(-15));

        schema.positive();
        Assertions.assertTrue(schema.isValid(15));
        Assertions.assertFalse(schema.isValid(-15));

        schema.range(9, 20);
        Assertions.assertTrue(schema.isValid(17));
        Assertions.assertFalse(schema.isValid(7));
    }

    @Test
    public void testMapSchema() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, String> data = new HashMap<>();

        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(data));
        Assertions.assertFalse(schema.isValid(15));

        schema.required();
        Assertions.assertFalse(schema.isValid(null));
        data.put("key", "value");
        Assertions.assertTrue(schema.isValid(data));

        schema.sizeof(2);
        Assertions.assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        Assertions.assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        Assertions.assertFalse(schema.isValid(data));
    }

    @Test
    public void testMapSchemaShaped() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();
        Map<String, BaseSchema> mapSchemas = new HashMap<>();
        mapSchemas.put("name", validator.string().required());
        mapSchemas.put("age", validator.number().positive());
        mapSchema.shape(mapSchemas);

        Map<String, Object> testFirstMap = new HashMap<>();
        testFirstMap.put("name", "Kolya");
        testFirstMap.put("age", 18);
        assertTrue(mapSchema.isValid(testFirstMap));

        Map<String, Object> testSecondMap = new HashMap<>();
        testSecondMap.put("name", "Maya");
        testSecondMap.put("age", null);
        assertTrue(mapSchema.isValid(testSecondMap));

        Map<String, Object> testThirdMap = new HashMap<>();
        testThirdMap.put("name", "");
        testThirdMap.put("age", null);
        assertFalse(mapSchema.isValid(testThirdMap));

        Map<String, Object> testForthMap = new HashMap<>();
        testForthMap.put("name", "Valya");
        testForthMap.put("age", -5);
        assertFalse(mapSchema.isValid(testForthMap));
    }
}
