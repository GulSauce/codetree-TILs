import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int Q;
        ArrayList<Command> commands = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        Q = toInt(st);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int mainCommand = toInt(st);
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, toInt(st)));
            }
            if (mainCommand == 2) {
                commands.add(new Command(mainCommand, toInt(st), toInt(st)));
            }
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, toInt(st), toInt(st)));
            }
            if (mainCommand == 4) {
                commands.add(new Command(mainCommand, toInt(st)));
            }
        }

        new Solver(N, commands).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    ArrayList<Command> commands;
    Node[] nodes;

    public Solver(
        int endNodeNumber,
        ArrayList<Command> commands
    ) {
        this.nodes = new Node[endNodeNumber + 1];
        this.endNodeNumber = endNodeNumber;
        this.commands = commands;
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            Node head = new Node(0);
            Node tail = new Node(0);
            Node cur = new Node(v);
            
            head.connectToRight(cur);
            cur.connectToRight(tail);
            nodes[v] = cur;
        }

        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node target = nodes[command.value1];
                Node prev = target.prev;
                Node next = target.next;

                prev.disconnectFromRight();
                target.disconnectFromRight();
                prev.connectToRight(next);

                Node newHead = new Node(0);
                Node newTail = new Node(0);

                newHead.connectToRight(target);
                target.connectToRight(newTail);
            }
            if (command.mainCommand == 2) {
                Node toMove = nodes[command.value2];
                Node toMovePrev = toMove.prev;
                Node toMoveNext = toMove.next;
                toMovePrev.disconnectFromRight();
                toMove.disconnectFromRight();

                toMovePrev.connectToRight(toMoveNext);

                Node toIn = nodes[command.value1];
                Node toInPrev = toIn.prev;

                toInPrev.disconnectFromRight();
                toInPrev.connectToRight(toMove);
                toMove.connectToRight(toIn);
            }

            if (command.mainCommand == 3) {
                Node toMove = nodes[command.value2];
                Node toMovePrev = toMove.prev;
                Node toMoveNext = toMove.next;
                toMovePrev.disconnectFromRight();
                toMove.disconnectFromRight();

                toMovePrev.connectToRight(toMoveNext);

                Node toIn = nodes[command.value1];
                Node toInNext = toIn.next;

                toIn.disconnectFromRight();
                toIn.connectToRight(toMove);
                toMove.connectToRight(toInNext);
            }
            if (command.mainCommand == 4) {
                Node cur = nodes[command.value1];
                System.out.println(cur.prev.value + " " + cur.next.value);
            }
        }
        for (int v = 1; v <= endNodeNumber; v++) {
            System.out.print(nodes[v].next.value + " ");
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

    public void connectToRight(Node node) {
        node.prev = this;
        this.next = node;
    }

    public void disconnectFromRight() {
        if (this.next != null) {
            next.prev = null;
        }
        this.next = null;
    }
}

class Command {

    int mainCommand;
    int value1;
    int value2;

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
}