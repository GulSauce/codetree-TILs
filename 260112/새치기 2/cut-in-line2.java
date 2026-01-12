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
        int x = personCount / lineCount;
        for (int i = 0; i <= lineCount; i++) {
            lineHeaders[i] = new Node("HEADER");
        }

        String prevName = "";
        for (int i = 1; i <= personCount; i++) {
            int lineNumber = (i + x - 1) / x;
            int sequence = (i - 1) % x + 1;

            String curName = names.get(i);
            Node cur = new Node(curName);
            nameNodeHashMap.put(curName, cur);
            if (sequence == 1) {
                lineHeaders[lineNumber].connectToRight(cur);
            } else {
                nameNodeHashMap.get(prevName).connectToRight(cur);
            }
            if (sequence == x) {
                Node tail = new Node("TAIL");
                cur.connectToRight(tail);
            }
            prevName = curName;
        }

        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node a = nameNodeHashMap.get(command.value1);
                Node b = nameNodeHashMap.get(command.value2);

                Node aPrev = a.prev;
                Node aNext = a.next;
                aPrev.disconnectFromRight();
                a.disconnectFromRight();
                aPrev.connectToRight(aNext);

                Node bPrev = b.prev;
                bPrev.disconnectFromRight();
                bPrev.connectToRight(a);
                a.connectToRight(b);
            }

            if (command.mainCommand == 2) {
                Node a = nameNodeHashMap.get(command.value1);
                Node aPrev = a.prev;
                Node aNext = a.next;
                aPrev.disconnectFromRight();
                a.disconnectFromRight();
                aPrev.connectToRight(aNext);
            }

            if (command.mainCommand == 3) {
                Node rangeStart = nameNodeHashMap.get(command.value1);
                Node rangeEnd = nameNodeHashMap.get(command.value2);
                Node c = nameNodeHashMap.get(command.value3);

                Node cPrev = c.prev;
                Node cNext = c.next;

                Node rangePrev = rangeStart.prev;
                Node rangeNext = rangeEnd.next;
                if (c.next == rangeStart) {
                    c.connectToRight(rangeNext);
                    rangeEnd.connectToRight(c);
                    cPrev.connectToRight(rangeStart);
                } else if (rangeEnd.next == c) {
                    continue;
                } else {
                    rangePrev.disconnectFromRight();
                    rangeEnd.disconnectFromRight();
                    rangePrev.connectToRight(rangeNext);

                    cPrev.connectToRight(rangeStart);
                    rangeEnd.connectToRight(c);
                }
            }
        }

        for (int i = 1; i <= lineCount; i++) {
            Node lineHeader = lineHeaders[i];
            Node cur = lineHeader.next;
            if (cur.value.equals("TAIL")) {
                System.out.println(-1);
                continue;
            } else {
                while (!cur.value.equals("TAIL")) {
                    System.out.print(cur.value + " ");
                    cur = cur.next;
                }
            }
            System.out.println();
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

    public void disconnectFromRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void connectToRight(Node target) {
        this.next = target;
        target.prev = this;
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