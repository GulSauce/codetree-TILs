import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Command> commands = new ArrayList<>();
        int k;
        int v;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            HashCommand hashCommand = HashCommand.valueOf(sc.next());
            if (hashCommand == HashCommand.add) {
                k = sc.nextInt();
                v = sc.nextInt();
                commands.add(new Command(hashCommand, k, v));
            } else {
                k = sc.nextInt();
                commands.add(new Command(hashCommand, k));
            }
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    List<Command> commands;

    public Solver(
            List<Command> commands
    ) {
        this.commands = commands;
    }

    public void solve() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Command command : commands) {
            if (command.hashCommand == HashCommand.add) {
                hashMap.put(command.key, command.value);
            }
            if (command.hashCommand == HashCommand.remove) {
                hashMap.remove(command.key);
            }
            if (command.hashCommand == HashCommand.find) {
                if (hashMap.containsKey(command.key)) {
                    System.out.println(hashMap.get(command.key));
                } else {
                    System.out.println("None");
                }
            }
        }
    }
}

class Command {
    HashCommand hashCommand;
    int key;
    int value;

    public Command(
            HashCommand hashCommand,
            int key
    ) {
        this.hashCommand = hashCommand;
        this.key = key;
    }

    public Command(
            HashCommand hashCommand,
            int key,
            int value
    ) {
        this.hashCommand = hashCommand;
        this.key = key;
        this.value = value;
    }
}

enum HashCommand {
    add,
    find,
    remove
}