package hexlet.code;
public class Validator {
    private StringSchema schema;

    public Validator() {
        this.schema = new StringSchema();
    }
    public StringSchema string() {
        return schema;
    }
}
