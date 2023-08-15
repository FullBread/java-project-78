package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        Predicate<Object> mapPredicate = value -> value instanceof Map;
        validations.add(mapPredicate);
    }
    public MapSchema required() {
        isRequired = true;
        return this;
    }
    public MapSchema sizeof(int size) {
        Predicate<Object> sizeofPredicate = value -> ((Map<?, ?>) value).size() == size;
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
