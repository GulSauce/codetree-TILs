import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int q;
        List<CommandSet> commands = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        q = toInt(st);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            Command command = Command.valueOf(st.nextToken());
            if (Command.clear == command) {
                commands.add(new CommandSet(command));
            } else {
                commands.add(new CommandSet(command, toInt(st)));
            }
        }
        new Solver(commands).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int bitMask = 0;
    List<CommandSet> commandSets;

    public Solver(List<CommandSet> commandSets) {
        this.commandSets = commandSets;
    }

    public void solve() {
        for (CommandSet commandSet : commandSets) {
            if (commandSet.command == Command.add) {
                bitMask = bitMask | (1 << commandSet.value);
            }
            if (commandSet.command == Command.delete) {
                bitMask = bitMask & ~(1 << commandSet.value);
            }
            if (commandSet.command == Command.print) {
                int exist = (bitMask >> commandSet.value) & 1;
                System.out.println(exist);
            }
            if (commandSet.command == Command.toggle) {
                bitMask = bitMask ^ (1 << commandSet.value);
            }
            if (commandSet.command == Command.clear) {
                bitMask = 0;
            }
        }
    }
}

class CommandSet {

    Command command;
    int value;

    public CommandSet(Command command) {
        this.command = command;
    }

    public CommandSet(Command command, int value) {
        this.command = command;
        this.value = value;
    }
}


enum Command {
    add,
    delete,
    print,
    toggle,
    clear
}