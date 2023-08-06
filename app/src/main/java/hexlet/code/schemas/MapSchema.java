package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        isRequired = true;
        Predicate<Object> requiredPredicate = value -> (value instanceof Map) && nullCheck(((Map) value));
        validations.add(requiredPredicate);
        return this;
    }
    public MapSchema sizeof(int number) {
        Predicate<Object> sizeofPredicate = value -> value instanceof Map && ((Map<?, ?>) value).size() == number;
        validations.add(sizeofPredicate);
        return this;
    }
    public static boolean nullCheck(Map<?, ?> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                return false;
            }
        }
        return true;
    }
    public MapSchema shape(Map<String, BaseSchema> schema) {
        Predicate<Object> shape = s -> shapeIsRequired(schema, (Map<?, ?>) s);
        validations.add(shape);
        return this;
    }

    private boolean shapeIsRequired(Map<String, BaseSchema> schema, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> mapEntry : schema.entrySet()) {
            String key = mapEntry.getKey();
            if (!map.containsKey(key) || !mapEntry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
