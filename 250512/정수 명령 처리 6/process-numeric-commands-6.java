import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            MainCommand mainCommand = MainCommand.valueOf(sc.next());
            if (mainCommand == MainCommand.push) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            } else {
                commands.add(new Command(mainCommand));
            }
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    ArrayList<Command> commands;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public Solver(
            ArrayList<Command> commands
    ) {
        this.commands = commands;
    }

    public void solve() {
        for (Command command : commands) {
            switch (command.mainCommand) {
                case push:
                    priorityQueue.add(-command.value);
                    break;
                case pop:
                    System.out.println(-priorityQueue.poll());
                    break;
                case size:
                    System.out.println(priorityQueue.size());
                    break;
                case empty:
                    if (priorityQueue.isEmpty()) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                    break;
                case top:
                    System.out.println(-priorityQueue.peek());
                    break;
            }
        }
    }
}

class Command {
    MainCommand mainCommand;
    int value;

    public Command(
            MainCommand mainCommand
    ) {
        this.mainCommand = mainCommand;
    }

    public Command(
            MainCommand mainCommand,
            int value
    ) {
        this.mainCommand = mainCommand;
        this.value = value;
    }
}

enum MainCommand {
    push,
    pop,
    size,
    empty,
    top
}
