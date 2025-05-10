import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            TreeSetCommand treeSetCommand = TreeSetCommand.valueOf(sc.next());
            if (treeSetCommand == TreeSetCommand.largest) {
                commands.add(new Command(treeSetCommand));
                continue;
            }
            if (treeSetCommand == TreeSetCommand.smallest) {
                commands.add(new Command(treeSetCommand));
                continue;
            }
            commands.add(new Command(treeSetCommand, sc.nextInt()));
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    ArrayList<Command> commands;
    TreeSet<Integer> treeSet = new TreeSet<>();

    public Solver(
            ArrayList<Command> commands

    ) {
        this.commands = commands;
    }

    public void solve() {
        for (Command command : commands) {
            TreeSetCommand treeSetCommand = command.treeSetCommand;
            int value = command.value;
            switch (treeSetCommand) {
                case add:
                    treeSet.add(value);
                    break;
                case remove:
                    treeSet.remove(value);
                    break;
                case find:
                    System.out.println(treeSet.contains(value));
                    break;
                case lower_bound:
                    Integer lowerBound = treeSet.ceiling(value);
                    if (lowerBound == null) {
                        System.out.println("None");
                    } else {
                        System.out.println(lowerBound);
                    }
                    break;
                case upper_bound:
                    Integer upperBound = treeSet.higher(value);
                    if (upperBound == null) {
                        System.out.println("None");
                    } else {
                        System.out.println(upperBound);
                    }
                    break;
                case largest:
                    if (treeSet.isEmpty()) {
                        System.out.println("None");
                    } else {
                        System.out.println(treeSet.last());
                    }
                    break;
                case smallest:
                    if (treeSet.isEmpty()) {
                        System.out.println("None");
                    } else {
                        System.out.println(treeSet.first());
                    }
                    break;
            }
        }
    }
}

class Command {
    TreeSetCommand treeSetCommand;
    int value;

    public Command(
            TreeSetCommand treeSetCommand
    ) {
        this.treeSetCommand = treeSetCommand;
    }

    public Command(
            TreeSetCommand treeSetCommand,
            int value
    ) {
        this.treeSetCommand = treeSetCommand;
        this.value = value;
    }
}

enum TreeSetCommand {
    add,
    remove,
    find,
    lower_bound,
    upper_bound,
    largest,
    smallest
}