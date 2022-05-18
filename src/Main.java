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
    }
}