import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int Q;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            commands.add(new Command(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Solver(N, commands).solve();
    }
}

class Solver {
    int maxNumber;
    ArrayList<Command> commands;
    Node[] nodes;

    public Solver(
            int maxNumber,
            ArrayList<Command> commands
    ) {
        this.nodes = new Node[maxNumber + 2];
        this.maxNumber = maxNumber;
        this.commands = commands;
    }

    public void solve() {
        Node cur = new Node(0);
        nodes[0] = cur;
        for (int i = 1; i <= maxNumber + 1; i++) {
            cur.insertNext(new Node(i));
            nodes[i] = cur.next;
            cur = cur.next;
        }

        for (Command command : commands) {
            Node aStart = nodes[command.aStart];
            Node aEnd = nodes[command.aEnd];
            Node aPrev = aStart.prev;
            Node aNext = aEnd.next;

            Node bStart = nodes[command.bStart];
            Node bEnd = nodes[command.bEnd];
            Node bPrev = bStart.prev;
            Node bNext = bEnd.next;

            if (aPrev == bEnd) {
                bPrev.connectToRight(aStart);
                aEnd.connectToRight(bStart);
                bEnd.connectToRight(aNext);
            } else if (bPrev == aEnd) {
                aPrev.connectToRight(bStart);
                bEnd.connectToRight(aStart);
                aEnd.connectToRight(bNext);
            } else {
                aPrev.disConnectToRight();
                aEnd.disConnectToRight();
                aPrev.connectToRight(aNext);

                bPrev.disConnectToRight();
                bEnd.disConnectToRight();
                bPrev.connectToRight(bNext);

                bEnd.connectToRight(aPrev.next);
                aPrev.connectToRight(bStart);

                aEnd.connectToRight(bPrev.next);
                bPrev.connectToRight(aStart);
            }
        }

        Node head = nodes[0];
        printNode(head);
    }

    private void printNode(Node head) {
        Node cur = head.next;
        while (cur.next != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
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

    public void insertNext(Node target) {
        target.prev = this;
        target.next = this.next;

        this.next = target;
    }

    public void connectToRight(Node target) {
        this.next = target;
        if (target != null) {
            target.prev = this;
        }
    }

    public void disConnectToRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }
}

class Command {
    int aStart;
    int aEnd;
    int bStart;
    int bEnd;

    public Command(
            int aStart,
            int aEnd,
            int bStart,
            int bEnd
    ) {
        this.aStart = aStart;
        this.aEnd = aEnd;
        this.bStart = bStart;
        this.bEnd = bEnd;
    }

}