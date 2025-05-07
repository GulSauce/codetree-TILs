import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            commands.add(new Command(
                    HashSetCommand.valueOf(sc.next()),
                    sc.nextInt()
            ));
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
        HashSet<Integer> hashSet = new HashSet<>();
        for (Command command : commands) {
            switch (command.hashSetCommand) {
                case add:
                    hashSet.add(command.value);
                    break;
                case remove:
                    hashSet.remove(command.value);
                    break;
                case find:
                    System.out.println(hashSet.contains(command.value));
                    break;
            }
        }
    }
}

class Command {
    HashSetCommand hashSetCommand;
    int value;

    public Command(
            HashSetCommand hashSetCommand,
            int value
    ) {
        this.hashSetCommand = hashSetCommand;
        this.value = value;
    }
}

enum HashSetCommand {
    add,
    remove,
    find
}