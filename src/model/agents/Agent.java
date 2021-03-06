package model.agents;

import config.AgentConfig;
import config.Functions;

public class Agent {
    private final int metabolismRate;
    private int sugarStock;
    private final int vision;


    public Agent() {
        this.metabolismRate = Functions.getRandomInRange(AgentConfig.MIN_METABOLISM.getValue(), AgentConfig.MAX_METABOLISM.getValue());
        this.sugarStock = Functions.getRandomInRange(this.metabolismRate + 1, AgentConfig.MAX_SUGAR.getValue());
        this.vision = Functions.getRandomInRange(AgentConfig.MIN_VISION.getValue(), AgentConfig.MAX_VISION.getValue());
    }

    public void metabolize() {
        this.sugarStock -= this.metabolismRate;
    }

    public boolean isAlive() {
        return this.sugarStock >= 0;
    }

    public void takeSugar(int sugarAmount) {
        this.sugarStock += sugarAmount;
    }

    public int getMetabolismRate() {
        return this.metabolismRate;
    }

    public int getSugarStock() {
        return this.sugarStock;
    }

    public int getVision() {
        return this.vision;
    }

}
