package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positivePredicate = value -> (value instanceof Integer) && ((Integer) value > 0);
        validations.add(positivePredicate);
        return this;
    }

    public NumberSchema range(int first, int last) {
        Predicate<Object> rangePredicate;
        if (first <= last) {
            rangePredicate = value -> ((Integer) value) >= first && ((Integer) value) <= last;
        } else {
            rangePredicate = value -> ((Integer) value) >= last && ((Integer) value) <= first;
        }
        validations.add(rangePredicate);
        return this;
    }
}
