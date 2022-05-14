package config;

public enum SugarConfig {
    APPEARANCE_PROBABILITY(50),
    MIN_SUGAR(1),
    MAX_SUGAR(10);

    private int value;

    SugarConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


