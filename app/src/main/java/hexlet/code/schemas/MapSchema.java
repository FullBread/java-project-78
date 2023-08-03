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
        if (map ==null) {
            throw new IllegalArgumentException("Map must not be null");
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                return false;
            }
        }
        return true;
    }
}
