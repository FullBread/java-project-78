package hexlet.code;

public class StringSchema {
    private boolean required;
    private Integer minLength;
    private String contains;

    public StringSchema() {
        this.required = false;
        this.minLength = null;
        this.contains = null;
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

    public boolean isValid(String data) {
        if (required && (data == null || data.isEmpty())) {
            return false;
        }

        if (minLength != null && data != null && data.length() < minLength) {
            return false;
        }

        if (contains != null && data != null && !data.contains(contains)) {
            return false;
        }

        return true;
    }
}
