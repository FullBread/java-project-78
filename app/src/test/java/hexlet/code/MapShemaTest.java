package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapShemaTest {

    @Test
    public void testNullIsValid() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testRequiredWithNull() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testRequiredWithEmptyMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();
        assertTrue(schema.isValid(new HashMap()));
        assertFalse(schema.isValid("test"));
    }

    @Test
    public void testRequiredWithNonEmptyMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeOfWithNonEmptyMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
    }

    @Test
    public void testSizeOfWithCorrectSizeMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        assertTrue(schema.isValid(null));
    }
    @Test
    public void testSizeOfWithLargerMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }
    @Test
    public void testSizeOfWithSmallerMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
    }
    @Test
    public void testRequiredWithNullStringValues() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();
        Map<String, String> data = new HashMap<>();
        data.put("key1", null);
        assertFalse(schema.isValid(data));
    }
    @Test
    public void testMapShape() {
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
