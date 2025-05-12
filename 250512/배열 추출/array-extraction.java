import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            commands.add(sc.nextInt());
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    ArrayList<Integer> commands;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    public Solver(
            ArrayList<Integer> commands
    ) {
        this.commands = commands;
    }

    public void solve() {
        for (Integer command : commands) {
            if (command == 0) {
                if (priorityQueue.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(priorityQueue.poll());
                continue;
            }
            priorityQueue.add(command);
        }
    }
}