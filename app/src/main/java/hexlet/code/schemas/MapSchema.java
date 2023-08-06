package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        isRequired = true;
        Predicate<Object> requiredPredicate = value -> value instanceof Map;
        validations.add(requiredPredicate);
        return this;
    }
    public MapSchema sizeof(int number) {
        Predicate<Object> sizeofPredicate = value -> ((Map<?, ?>) value).size() == number;
        validations.add(sizeofPredicate);
        return this;
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
