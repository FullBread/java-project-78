package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected List<Predicate<Object>> validations = new LinkedList<>();
    protected boolean isRequired = false;
    public final boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }
        for (Predicate<Object> validation: validations) {
            if (!(validation.test(value))) {
                return false;
            }
        }
        return true;
    }
}
