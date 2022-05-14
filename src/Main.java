//import model.agents.Agent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a sugarscape size: ");
        int size = in.nextInt();
        System.out.print("Input an agents amount: ");
        int agentsAmount = in.nextInt();
        in.close();

        Sugarscape sugarscape = new Sugarscape(size, agentsAmount);
        sugarscape.init();

//        Agent agent = new Agent();
//        System.out.println(agent.getMetabolismRate());
//        System.out.println(agent.getSugarStock());
//        System.out.println(agent.getVision());
//        System.out.println("===");
//
//        Agent agent2 = new Agent();
//        System.out.println(agent2.getMetabolismRate());
//        System.out.println(agent2.getSugarStock());
//        System.out.println(agent2.getVision());
    }
}