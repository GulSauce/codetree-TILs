import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int K;
            ArrayList<Command> commands = new ArrayList<>();

            K = sc.nextInt();
            for (int j = 0; j < K; j++) {
                commands.add(new Command(MainCommand.valueOf(sc.next()), sc.nextInt()));
            }
            new Solver(commands).solve();
        }
        sc.close();
    }
}

class Solver {
    TreeSet<Integer> treeSet = new TreeSet<>();
    ArrayList<Command> commands;

    public Solver(
            ArrayList<Command> commands
    ) {
        this.commands = commands;
    }

    public void solve() {
        for (Command command : commands) {
            switch (command.mainCommand) {
                case I:
                    treeSet.add(command.value);
                    break;
                case D:
                    if (treeSet.isEmpty()) {
                        break;
                    }
                    if (command.value == 1) {
                        treeSet.remove(treeSet.last());
                    }
                    if (command.value == -1) {
                        treeSet.remove(treeSet.first());
                    }
                    break;
            }
        }
        if (!treeSet.isEmpty()) {
            System.out.println(treeSet.last() + " " + treeSet.first());
        } else {
            System.out.println("EMPTY");
        }
    }
}

class Command {
    MainCommand mainCommand;
    int value;

    public Command(
            MainCommand mainCommand,
            int value
    ) {
        this.mainCommand = mainCommand;
        this.value = value;
    }
}

enum MainCommand {
    I,
    D
}