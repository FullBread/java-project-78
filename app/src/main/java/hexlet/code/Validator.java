package hexlet.code;


import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
    private StringSchema string;
    private NumberSchema number;
    private MapSchema map;

    public Validator() {
        this.string = new StringSchema();
        this.number = new NumberSchema();
        this.map = new MapSchema();
    }
    public StringSchema string() {
        return string;
    }
    public NumberSchema number() {
        return number;
    }
    public MapSchema map() {
        return map;
    }
}
