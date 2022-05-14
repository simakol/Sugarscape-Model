import config.Color;
import config.Functions;
import config.SugarConfig;
import model.agents.Agent;
import model.agents.Sugar;
import model.cells.Cell;

import java.util.ArrayList;

public class Sugarscape {
    private int size;
    private int agentsAmount;
    private int aliveAgents;
    private int generationNum;
    private Cell[][] currentGeneration;
    private Cell[][] nextGeneration;
    private ArrayList<Sugar> occupiedSugarCells;

    public Sugarscape(int size, int agentsAmount) {
        try {
            if (size <= 0) {
                throw new SugarscapeError(Color.RED.getCode() + "Incorrect sugarscape size." + Color.RESET.getCode());
            }
            if (agentsAmount <= 0 || agentsAmount > (int) Math.pow((double) size, 2.0)) {
                throw new SugarscapeError(Color.RED.getCode() + "Wrong agents amount." + Color.RESET.getCode());

            }
            this.size = size;
            this.agentsAmount = agentsAmount;


        } catch (SugarscapeError ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        this.aliveAgents = agentsAmount;
        this.generationNum = 1;
        this.currentGeneration = new Cell[size][size];
        this.nextGeneration = new Cell[size][size];
        this.occupiedSugarCells = new ArrayList<Sugar>();
    }

    public void init() {
        initGenerations();
        placeAgents();
        placeSugar();
        while (true) {
            if (this.aliveAgents == 0) {
                System.out.print(Color.RED.getCode() + "All agents died! Game over!" + Color.RESET.getCode());
                System.exit(0);
            }

            visualize();
            calculateNextGeneration();

            this.occupiedSugarCells.clear();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

    private void initGenerations() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.currentGeneration[x][y] = new Cell();
                this.nextGeneration[x][y] = new Cell();
            }
        }
    }

    private void placeAgents() {
        for (int i = 0; i < this.agentsAmount; i++) {
            int x = Functions.getRandomInRange(0, this.size - 1);
            int y = Functions.getRandomInRange(0, this.size - 1);
            if (this.currentGeneration[x][y].isAgent()) {
                i--;
                continue;
            }
            Agent agent = new Agent();
            this.currentGeneration[x][y].setAgent(agent);

        }
    }

    private void placeSugar() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.currentGeneration[x][y].isEmpty() && Math.random() < (double) SugarConfig.APPEARANCE_PROBABILITY.getValue() / 100) {
                    Sugar sugar = new Sugar(x, y);
                    this.currentGeneration[x][y].setSugar(sugar);
                }

            }
        }
    }

    private void visualize() {
        this.aliveAgents = 0;
        System.out.println(Color.GREEN.getCode() + this.generationNum + Color.RESET.getCode() + " generation");

        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.currentGeneration[x][y].isEmpty())
                    System.out.print(Color.WHITE.getCode() + "  .  " + Color.RESET.getCode());
                else if (this.currentGeneration[x][y].isAgent()) {
                    this.aliveAgents++;
                    System.out.print(Color.BLACK.getCode() + "  #  " + Color.RESET.getCode());
                } else
//                    System.out.print(this.currentGeneration[x][y].getSugar().getSugarColor() + " * " + Color.RESET.getCode());
                    System.out.print(this.currentGeneration[x][y].getSugar().getSugarColor() + "  " + this.currentGeneration[x][y].getSugar().getSugarAmount() + "  " + Color.RESET.getCode());

            }
            System.out.println();

        }
        System.out.println();

    }

    private void calculateNextGeneration() {
        this.generationNum++;

        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.currentGeneration[x][y].isEmpty() && Math.random() < (double) SugarConfig.APPEARANCE_PROBABILITY.getValue() / 100) {
                    Sugar sugar = new Sugar(x, y);
                    this.nextGeneration[x][y] = new Cell(sugar);
                } else if (this.currentGeneration[x][y].isAgent()) {
                    moveAgent(x, y);
//                    System.out.println(this.currentGeneration[x][y].getAgent().getSugarStock());
//                    this.nextGeneration[x][y] = this.currentGeneration[x][y];

                } else
                    this.nextGeneration[x][y] = this.currentGeneration[x][y];
            }
        }
        nextGenToCurrent();

    }

    private void moveAgent(int x, int y) {
//        System.out.println(this.currentGeneration[x][y].getAgent() + " element");
//        System.out.println(this.currentGeneration[x][y].getAgent().getMetabolismRate() + " rate");
//        System.out.println(this.currentGeneration[x][y].getAgent().getSugarStock() + " before");

//   this.nextGeneration[x][y].clear();
        this.currentGeneration[x][y].getAgent().metabolize();

//        System.out.println(this.currentGeneration[x][y].getAgent().getSugarStock() + " after");

        if (!this.currentGeneration[x][y].getAgent().isAlive()) {
//            System.out.println("dead");
            this.nextGeneration[x][y].clear();
            return;
        }
        Sugar targetSugar = chooseTargetSugar(x, y);


        if (targetSugar == null) {
            // добавить движения, если рядом вообще нет сахара(в рандомное направление на одну клетку)
            System.out.println("no sugar");
        } else {
//            System.out.println("x = " + x + " | y = " + y + " | targetSugar = " + targetSugar.getSugarAmount() + " | currentSugar = " + this.currentGeneration[x][y].getAgent().getSugarStock());
// Доделать этот фрагмент
     /*       int sugarX = targetSugar.getXPos();
            int sugarY = targetSugar.getYPos();
//            System.out.println("x = " + sugarX + " | y = " + sugarY);
            this.currentGeneration[x][y].getAgent().takeSugar(targetSugar.getSugarAmount());
            Agent agent = this.currentGeneration[x][y].getAgent();
            this.nextGeneration[sugarX][sugarY].setAgent(agent);
//            this.nextGeneration[sugarX][sugarY] = this.currentGeneration[x][y];
            this.nextGeneration[x][y].clear();
            this.currentGeneration[x][y].clear();*/
        }


    }

    private Sugar chooseTargetSugar(int x, int y) {
        ArrayList<Sugar> sugars = new ArrayList<Sugar>();
        for (int xn = this.currentGeneration[x][y].getAgent().getVision() * -1; xn <= this.currentGeneration[x][y].getAgent().getVision(); xn++) {
            if (x + xn >= 0 && x + xn < this.size) {
                if (this.currentGeneration[x + xn][y].isSugar()) {
                    sugars.add(this.currentGeneration[x + xn][y].getSugar());
                }
            }
        }
        for (int yn = this.currentGeneration[x][y].getAgent().getVision() * -1; yn <= this.currentGeneration[x][y].getAgent().getVision(); yn++) {
            if (y + yn >= 0 && y + yn < this.size) {
                if (this.currentGeneration[x][y + yn].isSugar()) {
                    sugars.add(this.currentGeneration[x][y + yn].getSugar());
                }
            }
        }
        if (sugars.size() == 0) return null;

        Sugar maxSugarInCell = sugars.get(0);

        for (Sugar sugar : sugars) {
            if (sugar.getSugarAmount() > maxSugarInCell.getSugarAmount() && !this.occupiedSugarCells.contains(sugar)) {
                maxSugarInCell = sugar;
                this.occupiedSugarCells.add(sugar);
            }
        }
//        System.out.println(maxSugarInCell.getSugarAmount());
        return maxSugarInCell;
    }

    private void nextGenToCurrent() {
//        for (int x = 0; x < this.size; x++) {
//            System.arraycopy(this.nextGeneration[x], 0, this.currentGeneration[x], 0, this.size);
//        }
        this.nextGeneration = this.currentGeneration.clone();
    }

    static class SugarscapeError extends Exception {

        public SugarscapeError(String message) {
            super(message);
        }

    }

}
