import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String init;
        int N;
        List<Command> commands = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        init = st.nextToken();

        st = new StringTokenizer(br.readLine());
        N = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int value = toInt(st);
            if (value == 3 || value == 4) {
                commands.add(new Command(value, ""));
            } else {
                commands.add(new Command(value, st.nextToken()));
            }
        }

        new Solver(init, commands).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    String init;
    List<Command> commands;

    final String nullStr = "(Null)";

    public Solver(String init, List<Command> commands) {
        this.init = init;
        this.commands = commands;
    }

    public void solve() {
        Node cur = new Node(init);
        Node head = new Node(nullStr);
        Node tail = new Node(nullStr);

        cur.prev = head;
        cur.next = tail;

        for (Command command : commands) {
            if (command.command == 1) {
                Node prev = cur.prev;
                prev.disconnectFromRight();

                Node newNode = new Node(command.value);
                prev.connectToRight(newNode);
                newNode.connectToRight(cur);
                printAnswer(cur);
            }
            if (command.command == 2) {
                Node next = cur.next;
                cur.disconnectFromRight();

                Node newNode = new Node(command.value);
                cur.connectToRight(newNode);
                newNode.connectToRight(next);
                printAnswer(cur);
            }
            if (command.command == 3) {
                Node prev = cur.prev;
                if (!prev.value.equals(nullStr)) {
                    cur = prev;
                }
                printAnswer(cur);
            }
            if (command.command == 4) {
                Node next = cur.next;
                if (!next.value.equals(nullStr)) {
                    cur = next;
                }
                printAnswer(cur);
            }
        }
    }

    private void printAnswer(Node cur) {
        System.out.println(cur.prev.value + " " + cur.value + " " + cur.next.value);
    }
}

class Node {

    String value;
    Node prev;
    Node next;

    public Node(String value) {
        this.value = value;
    }

    public void disconnectFromRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void connectToRight(Node node) {
        node.prev = this;
        this.next = node;
    }
}

class Command {

    int command;
    String value;

    public Command(int command, String value) {
        this.command = command;
        this.value = value;
    }
}