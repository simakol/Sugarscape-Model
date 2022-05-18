package config;

public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m");
    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
