import config.Color;
import config.Functions;
import config.SugarConfig;
import model.agents.Agent;
import model.agents.Sugar;
import model.cells.Cell;

public class Sugarscape {
    private int size;
    private int agentsAmount;
    private int generationNum;
    private Cell[][] currentGeneration;
    private Cell[][] nextGeneration;

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

        this.generationNum = 1;
        this.currentGeneration = new Cell[size][size];
        this.nextGeneration = new Cell[size][size];
    }

    public void init() {
        initGenerations();
        placeAgents();
        placeSugar();
        while (true) {
            visualize();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

    public void initGenerations() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.currentGeneration[x][y] = new Cell();
                this.nextGeneration[x][y] = new Cell();
            }
        }
    }

    public void placeAgents() {
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

    public void placeSugar() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.currentGeneration[x][y].isEmpty() && Math.random() < (double) SugarConfig.APPEARANCE_PROBABILITY.getValue() / 100) {
                    Sugar sugar = new Sugar(x, y);
                    this.currentGeneration[x][y].setSugar(sugar);
                }

            }
        }
    }

    public void visualize() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.currentGeneration[x][y].isEmpty())
                    System.out.print(Color.WHITE.getCode() + " . " + Color.RESET.getCode());
                else if (this.currentGeneration[x][y].isAgent())
                    System.out.print(Color.BLACK.getCode() + " # " + Color.RESET.getCode());
                else
                    System.out.print(this.currentGeneration[x][y].getSugar().getSugarColor() + " * " + Color.RESET.getCode());

            }
            System.out.println();

        }
        System.out.println();

    }


    class SugarscapeError extends Exception {

        public SugarscapeError(String message) {
            super(message);
        }

    }

}
