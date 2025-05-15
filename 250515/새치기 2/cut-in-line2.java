import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M, Q;
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            names.add(sc.next());
        }
        for (int i = 0; i < Q; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, sc.next(), sc.next()));
            }
            if (mainCommand == 2) {
                commands.add(new Command(mainCommand, sc.next()));
            }
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, sc.next(), sc.next(), sc.next()));
            }
        }
        sc.close();

        new Solver(N, M, names, commands).solve();
    }
}

class Solver {
    int personCount;
    int lineCount;
    ArrayList<String> names;
    ArrayList<Command> commands;
    HashMap<String, Node> nameNodeHashMap = new HashMap<>();

    Node[] lineHeaders;

    public Solver(
            int personCount,
            int lineCount,
            ArrayList<String> names,
            ArrayList<Command> commands
    ) {
        this.personCount = personCount;
        this.lineCount = lineCount;
        this.names = names;
        this.commands = commands;
        this.lineHeaders = new Node[lineCount + 1];
    }

    public void solve() {
        init();
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node cur = nameNodeHashMap.get(command.value1);
                cur.pop();
                Node target = nameNodeHashMap.get(command.value2);
                target.prev.insertToRight(cur);
            }
            if (command.mainCommand == 2) {
                Node cur = nameNodeHashMap.get(command.value1);
                cur.pop();
            }
            if (command.mainCommand == 3) {
                Node start = nameNodeHashMap.get(command.value1);
                Node end = nameNodeHashMap.get(command.value2);
                Node target = nameNodeHashMap.get(command.value3);
                Node prev = start.prev;
                Node next = end.next;
                prev.disconnectToRight();
                end.disconnectToRight();
                prev.connectToRight(next);

                Node targetPrev = target.prev;
                targetPrev.disconnectToRight();
                targetPrev.connectToRight(start);
                end.connectToRight(target);
            }
        }
        printAnswer();
    }

    private void printAnswer() {
        for (int i = 1; i <= lineCount; i++) {
            Node head = lineHeaders[i];
            if (isTail(head.next)) {
                System.out.println(-1);
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
        for (int i = 1; i < lineHeaders.length; i++) {
            lineHeaders[i] = new Node("%HEAD%");
        }

        int X = personCount / lineCount;
        Node prev = null;
        for (int i = 1; i < names.size(); i++) {
            int lineNumber = (int) Math.ceil((double) i / X);
            int standNumber = (i - 1) % X + 1;

            Node cur = new Node(names.get(i));
            nameNodeHashMap.put(cur.value, cur);
            if (standNumber == 1) {
                lineHeaders[lineNumber].connectToRight(cur);
            } else {
                prev.connectToRight(cur);
                if (standNumber == X) {
                    cur.connectToRight(new Node("%TAIL%"));
                }
            }
            prev = cur;
        }
    }
}

class Node {
    String value;
    Node prev;
    Node next;

    public Node(
            String value
    ) {
        this.value = value;
    }

    public void disconnectToRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void insertToRight(Node target) {
        target.prev = this;
        target.next = this.next;

        if (this.next != null) {
            this.next.prev = target;
        }

        this.next = target;
    }

    public void connectToRight(Node target) {
        this.next = target;
        target.prev = this;
    }

    public void pop() {
        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        this.next = null;
        this.prev = null;
    }
}

class Command {
    int mainCommand;
    String value1;
    String value2;
    String value3;

    public Command(
            int mainCommand,
            String value1
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
    }

    public Command(
            int mainCommand,
            String value1,
            String value2
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
        this.value2 = value2;
    }

    public Command(
            int mainCommand,
            String value1,
            String value2,
            String value3
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }
}