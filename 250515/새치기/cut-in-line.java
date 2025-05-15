import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M, Q;
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < M; i++) {
            ArrayList<Integer> line = new ArrayList<>();
            int personCount = sc.nextInt();
            for (int j = 0; j < personCount; j++) {
                line.add(sc.nextInt());
            }
            lines.add(line);
        }
        for (int i = 0; i < Q; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt()));
            }
            if (mainCommand == 2) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            }
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }
        }
        sc.close();

        new Solver(N, M, lines, commands).solve();
    }
}

class Solver {
    int personCount;
    int lineCount;

    Node[] lineHeaders;
    Node[] nodes;

    ArrayList<Command> commands;
    ArrayList<ArrayList<Integer>> lines;

    public Solver(
            int personCount,
            int lineCount,
            ArrayList<ArrayList<Integer>> lines,
            ArrayList<Command> commands
    ) {
        this.personCount = personCount;
        this.lineCount = lineCount;
        this.lines = lines;
        this.commands = commands;
        this.nodes = new Node[personCount + 1];
        this.lineHeaders = new Node[lineCount];
    }

    public void solve() {
        init();
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node cur = nodes[command.value1];
                cur.pop();
                Node target = nodes[command.value2];
                target.prev.insertToRight(cur);
            }
            if (command.mainCommand == 2) {
                Node cur = nodes[command.value1];
                cur.pop();
            }
            if (command.mainCommand == 3) {
                Node start = nodes[command.value1];
                Node end = nodes[command.value2];
                Node target = nodes[command.value3];
                Node next = end.next;
                Node prev = start.prev;

                prev.disconnectToRight();
                end.disconnectToRight();
                prev.connectToRight(next);

                target.prev.connectToRight(start);
                end.connectToRight(target);
            }
        }
        printAnswer();
    }

    private void printAnswer() {
        for (int i = 0; i < lineCount; i++) {
            Node head = lineHeaders[i];
            if (isTail(head.next)) {
                System.out.println(-1);
                continue;
            }
            Node cur = head.next;
            while (!isTail(cur)) {
                System.out.print(cur.value + " ");
                Node next = cur.next;
                cur = next;
            }
            System.out.println();
        }
    }

    private boolean isTail(Node node) {
        return node.next == null;
    }

    private void init() {
        for (int i = 0; i < lineCount; i++) {
            lineHeaders[i] = new Node(-1);
        }
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<Integer> line = lines.get(i);
            Node cur = new Node(line.get(0));
            nodes[cur.value] = cur;
            lineHeaders[i].connectToRight(cur);
            for (int j = 1; j < line.size(); j++) {
                Node prev = cur;
                cur = new Node(line.get(j));
                prev.connectToRight(cur);
                nodes[cur.value] = cur;
            }
            cur.connectToRight(new Node(-1));
        }
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

    public void insertToRight(Node target) {
        target.prev = this;
        target.next = this.next;

        if (this.next != null) {
            this.next.prev = target;
        }
        this.next = target;
    }

    public void disconnectToRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void connectToRight(Node target) {
        this.next = target;
        target.prev = this;
    }

    public void pop() {
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        if (this.prev != null) {
            this.prev.next = this.next;
        }

        this.prev = null;
        this.next = null;
    }
}

class Command {
    int mainCommand;
    int value1;
    int value2;
    int value3;

    public Command(
            int mainCommand,
            int value1
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
    }

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
            int value1,
            int value2,
            int value3
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }


}