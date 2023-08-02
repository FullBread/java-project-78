package hexlet.code;


import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    private StringSchema string;
    private NumberSchema number;

    public Validator() {
        this.string = new StringSchema();
        this.number = new NumberSchema();
    }
    public StringSchema string() {
        return string;
    }
    public NumberSchema number() {
        return number;
    }
}
