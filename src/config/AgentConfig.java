package config;

public enum AgentConfig {
    MIN_METABOLISM(1),
    MAX_METABOLISM(4),
    MIN_SUGAR(0),
    MAX_SUGAR(10),
    MIN_VISION(1),
    MAX_VISION(5);

    private int value;
    AgentConfig(int value){
        this.value = value;
    }
    public int getValue(){ return value;}
}
