package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        Predicate<Object> integerPredicate = value -> value instanceof Integer;
        validations.add(integerPredicate);
    }

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positivePredicate = value -> ((Integer) value > 0);
        validations.add(positivePredicate);
        return this;
    }

    public NumberSchema range(int first, int last) {
        int minValue = Math.min(first, last);
        int maxValue = Math.max(first, last);
        Predicate<Object> rangePredicate = value -> ((Integer) value) >= minValue && ((Integer) value) <= maxValue;
        validations.add(rangePredicate);
        return this;
    }
}
