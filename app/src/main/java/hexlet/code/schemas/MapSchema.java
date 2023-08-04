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
    public MapSchema shape(Map<String, BaseSchema> shapeMap) {
        Predicate<Object> shapePredicate = value -> {
            if (!(value instanceof Map)) {
                return false;
            }
            Map<?, ?> mapValue = (Map<?, ?>) value;
            for (Map.Entry<String, BaseSchema> entry : shapeMap.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object fieldValue = mapValue.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
            return true;
        };
        validations.add(shapePredicate);
        return this;
    }
}
