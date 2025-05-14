import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int Q;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            } else {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt()));
            }
        }
        sc.close();

        new Solver(commands).solve();
    }
}

class Solver {
    ArrayList<Command> commands;
    Node[] nodes;

    public Solver(
            ArrayList<Command> commands
    ) {
        this.nodes = new Node[100002];
        this.commands = commands;
    }

    public void solve() {
        init();
        int curNumber = 1;
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                for (int i = 1; i < command.value2; i++) {
                    nodes[curNumber + i].connectToRight(nodes[curNumber + i + 1]);
                }
                Node cur = nodes[command.value1];
                Node first = nodes[curNumber + 1];
                Node last = nodes[curNumber + command.value2];
                last.connectToRight(cur.next);
                cur.connectToRight(first);
                int nextNumber = curNumber + command.value2;
                curNumber = nextNumber;
            }
            if (command.mainCommand == 2) {
                for (int i = 1; i < command.value2; i++) {
                    nodes[curNumber + i].connectToRight(nodes[curNumber + i + 1]);
                }
                Node cur = nodes[command.value1];
                Node first = nodes[curNumber + 1];
                Node last = nodes[curNumber + command.value2];
                cur.prev.connectToRight(first);
                last.connectToRight(cur);
                int nextNumber = curNumber + command.value2;
                curNumber = nextNumber;
            }
            if (command.mainCommand == 3) {
                Node cur = nodes[command.value1];
                if (isHead(cur.prev) || isTail(cur.next)) {
                    System.out.println(-1);
                    continue;
                }
                System.out.print(cur.prev.value);
                System.out.print(" ");
                System.out.print(cur.next.value);
                System.out.println();
            }
        }
    }

    private boolean isHead(Node node) {
        return node.prev == null;
    }

    private boolean isTail(Node node) {
        return node.next == null;
    }


    private void init() {
        for (int i = 0; i <= 100001; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0].connectToRight(nodes[1]);
        nodes[1].connectToRight(nodes[100001]);
    }

}

class Node {
    int value;
    Node prev;
    Node next;

    public Node(
            int value
    ) {
        this.value = value;
    }

    public void connectToRight(Node target) {
        this.next = target;
        target.prev = this;
    }
}

class Command {
    int mainCommand;
    int value1;
    int value2;

    public Command(
            int mainCommand,
            int value1,
            int value2
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
        this.value2 = value2;
    }

    public Command(
            int mainCommand,
            int value1
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
    }
}