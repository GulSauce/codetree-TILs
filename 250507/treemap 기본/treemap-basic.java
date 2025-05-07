import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            TreeMapCommand treeMapCommand = TreeMapCommand.valueOf(sc.next());
            if (treeMapCommand == TreeMapCommand.add) {
                commands.add(new Command(treeMapCommand, sc.nextInt(), sc.nextInt()));
                continue;
            }
            if (treeMapCommand == TreeMapCommand.print_list) {
                commands.add(new Command(treeMapCommand));
                continue;
            }
            commands.add(new Command(treeMapCommand, sc.nextInt()));
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    ArrayList<Command> commands;

    public Solver(
            ArrayList<Command> commands
    ) {
        this.commands = commands;
    }


    public void solve() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (Command command : commands) {
            if (command.treeMapCommand == TreeMapCommand.add) {
                treeMap.put(command.key, command.value);
            }
            if (command.treeMapCommand == TreeMapCommand.remove) {
                treeMap.remove(command.key);
            }
            if (command.treeMapCommand == TreeMapCommand.find) {
                if (!treeMap.containsKey(command.key)) {
                    System.out.println("None");
                    continue;
                }
                System.out.println(treeMap.get(command.key));
            }
            if (command.treeMapCommand == TreeMapCommand.print_list) {
                if (treeMap.isEmpty()) {
                    System.out.println("None");
                    continue;
                }
                for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                    System.out.print(entry.getValue() + " ");
                }
                System.out.println();
            }
        }
    }
}

class Command {
    TreeMapCommand treeMapCommand;
    int key;
    int value;

    public Command(
            TreeMapCommand treeMapCommand
    ) {
        this.treeMapCommand = treeMapCommand;
    }

    public Command(
            TreeMapCommand treeMapCommand,
            int key
    ) {
        this.treeMapCommand = treeMapCommand;
        this.key = key;
    }

    public Command(
            TreeMapCommand treeMapCommand,
            int key,
            int value
    ) {
        this.treeMapCommand = treeMapCommand;
        this.key = key;
        this.value = value;
    }
}

enum TreeMapCommand {
    add,
    remove,
    find,
    print_list
}