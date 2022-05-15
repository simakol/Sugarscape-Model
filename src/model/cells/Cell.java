package model.cells;

import model.agents.Agent;
import model.agents.Sugar;

public class Cell {
    private Agent agent;
    private Sugar sugar;
    private boolean unavailable;

    public Cell() {
        this.agent = null;
        this.sugar = null;
        this.unavailable = false;
    }

    public Cell(Sugar sugar) {
        this.sugar = sugar;
        this.agent = null;
    }

    public Cell(Agent agent) {
        this.agent = agent;
        this.sugar = null;
    }

    public void setUnavailable(boolean state) {
//        this.agent = null;
//        this.sugar = null;
        this.unavailable = state;
    }
    public boolean isUnavailable() {
        return this.unavailable;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
        this.sugar = null;
    }

    public Agent getAgent() {
        return this.agent;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
        this.agent = null;
    }

    public Sugar getSugar() {
        return this.sugar;
    }

    public boolean isAgent() {
        return this.agent != null;
    }

    public boolean isSugar() {
        return this.sugar != null;
    }

    public boolean isEmpty() {
        return !this.isAgent() && !this.isSugar();
    }

    public void clear() {
        this.agent = null;
        this.sugar = null;
    }
}
