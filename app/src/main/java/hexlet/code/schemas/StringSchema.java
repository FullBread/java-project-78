package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        Predicate<Object> stringPredicate = value -> value instanceof String;
        validations.add(stringPredicate);
    }
    public StringSchema required() {
        isRequired = true;
        Predicate<Object> requiredPredicate = value -> !((String) value).isEmpty();
        validations.add(requiredPredicate);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> minLengthPredicate = value -> ((String) value).length() >= length;
        validations.add(minLengthPredicate);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> containsPredicate = value -> ((String) value).contains(str);
        validations.add(containsPredicate);
        return this;
    }
}
