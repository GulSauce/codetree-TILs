import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        String initString;
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        initString = st.nextToken();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int mainCommand = Integer.parseInt(st.nextToken());
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, st.nextToken()));
            } else if (mainCommand == 2) {
                commands.add(new Command(mainCommand, st.nextToken()));
            } else {
                commands.add(new Command(mainCommand));
            }
        }
        br.close();

        new Solver(initString, commands).solve();
    }
}

class Solver {

    String initString;
    ArrayList<Command> commands;

    public Solver(
        String initString,
        ArrayList<Command> commands
    ) {
        this.initString = initString;
        this.commands = commands;
    }

    public void solve() {
        Node head = new Node("%HEAD%");
        Node tail = new Node("%TAIL%");
        head.insertNext(tail);
        Node cur = new Node(initString);
        head.insertNext(cur);
        for (Command command : commands) {
            int mainCommand = command.mainCommand;
            if (mainCommand == 1) {
                Node newNode = new Node(command.value);
                cur.prev.insertNext(newNode);
            }
            if (mainCommand == 2) {
                Node newNode = new Node(command.value);
                cur.insertNext(newNode);
            }
            if (mainCommand == 3) {
                if (!isHead(cur.prev)) {
                    cur = cur.prev;
                }
            }
            if (mainCommand == 4) {
                if (!isTail(cur.next)) {
                    cur = cur.next;
                }
            }
            cur.print();
        }
    }

    private boolean isHead(Node node) {
        return node.prev == null;
    }

    private boolean isTail(Node node) {
        return node.next == null;
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

    public void insertNext(Node target) {
        target.prev = this;
        target.next = this.next;

        if (this.next != null) {
            this.next.prev = target;
        }
        this.next = target;
    }

    public void print() {
        if (this.prev.prev == null) {
            System.out.print("(Null)");
        } else {
            System.out.print(prev.value);
        }
        System.out.print(" ");
        System.out.print(value);
        System.out.print(" ");
        if (this.next.next == null) {
            System.out.print("(Null)");
        } else {
            System.out.print(next.value);
        }
        System.out.println();
    }
}


class Command {

    int mainCommand;
    String value;

    public Command(
        int mainCommand,
        String value
    ) {
        this.mainCommand = mainCommand;
        this.value = value;
    }

    public Command(
        int mainCommand
    ) {
        this.mainCommand = mainCommand;
    }

}