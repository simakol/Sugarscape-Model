package model.agents;

import config.Color;
import config.Functions;
import config.SugarConfig;

public class Sugar {
    private int xPos;
    private int yPos;
    private int sugarAmount;
    private String sugarColor;

    public Sugar(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sugarAmount = Functions.getRandomInRange(SugarConfig.MIN_SUGAR.getValue(), SugarConfig.MAX_SUGAR.getValue());
        this.setSugarColor();
    }

    private void setSugarColor() {
        int parts = (int) Math.ceil(this.sugarAmount / 3.0);

        if (this.sugarAmount >= SugarConfig.MIN_SUGAR.getValue() && this.sugarAmount <= parts)
            this.sugarColor = Color.GREEN.getCode();
        else if (this.sugarAmount > parts && this.sugarAmount <= parts * 2)
            this.sugarColor = Color.YELLOW.getCode();
        else
            this.sugarColor = Color.RED.getCode();
    }

    public String getSugarColor() {
        return this.sugarColor;
    }

    public int getXPos() {
        return this.xPos;
    }

    public void setXPos(int newPosition) {
        this.xPos = newPosition;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setYPos(int newPosition) {
        this.yPos = newPosition;
    }

    public int getSugarAmount() {
        return this.sugarAmount;
    }
//    public void clearSugar(){
//        this.sugarAmount = 0;
//    }
}
