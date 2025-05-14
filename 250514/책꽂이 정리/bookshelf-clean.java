import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        int Q;
        ArrayList<Command> commands = new ArrayList<>();
        int i, j;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        Q = sc.nextInt();
        for (int count = 0; count < Q; count++) {
            int mainCommand = sc.nextInt();
            i = sc.nextInt();
            j = sc.nextInt();

            commands.add(new Command(mainCommand, i, j));
        }
        sc.close();

        new Solver(N, K, commands).solve();
    }
}

class Solver {
    int bookCount;
    int shelfCount;
    ArrayList<Command> commands;
    Node[][] headAndTail;

    public Solver(
            int N,
            int K,
            ArrayList<Command> commands
    ) {
        this.bookCount = N;
        this.shelfCount = K;
        this.commands = commands;
        this.headAndTail = new Node[K + 1][2];
    }


    public void solve() {
        init();
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node iHead = headAndTail[command.value1][0];
                if (isTail(iHead.next)) {
                    continue;
                }
                Node first = iHead.next;
                first.pop();

                Node jTail = headAndTail[command.value2][1];
                jTail.prev.insertNext(first);
            }
            if (command.mainCommand == 2) {
                Node iTail = headAndTail[command.value1][1];
                if (isHead(iTail.prev)) {
                    continue;
                }
                Node last = iTail.prev;
                last.pop();

                Node jHead = headAndTail[command.value2][0];
                jHead.insertNext(last);
            }
            if (command.mainCommand == 3) {
                Node iHead = headAndTail[command.value1][0];
                Node iTail = headAndTail[command.value1][1];

                Node first = iHead.next;
                Node last = iTail.prev;

                iHead.disConnectToRight();
                last.disConnectToRight();
                iHead.connectToRight(iTail);

                Node jHead = headAndTail[command.value2][0];
                Node jHeadNext = jHead.next;
                jHead.connectToRight(first);
                last.connectToRight(jHeadNext);
            }
            if (command.mainCommand == 4) {
                Node iHead = headAndTail[command.value1][0];
                Node iTail = headAndTail[command.value1][1];

                Node first = iHead.next;
                Node last = iTail.prev;

                iHead.disConnectToRight();
                last.disConnectToRight();
                iHead.connectToRight(iTail);

                Node jTail = headAndTail[command.value2][1];
                Node jTailPrev = jTail.prev;
                jTailPrev.connectToRight(first);
                last.connectToRight(jTail);
            }
        }
        printAnswer();
    }

    private void printAnswer() {
        Queue<Integer> answer = new LinkedList<>();
        for (int i = 1; i <= shelfCount; i++) {
            Node head = headAndTail[i][0];
            Node cur = head.next;
            while (cur.next != null) {
                answer.add(cur.value);
                Node next = cur.next;
                cur = next;
            }
            System.out.print(answer.size() + " ");
            while (!answer.isEmpty()) {
                System.out.print(answer.poll() + " ");
            }
            System.out.println();
        }
    }

    private boolean isTail(Node target) {
        return target.next == null;
    }

    private boolean isHead(Node target) {
        return target.prev == null;
    }

    private void init() {
        Node node = new Node(0);
        headAndTail[1][0] = node;
        for (int i = 1; i <= bookCount + 1; i++) {
            node.insertNext(new Node(i));
            node = node.next;
        }
        headAndTail[1][1] = node;

        for (int i = 2; i <= shelfCount; i++) {
            Node head = new Node(0);
            Node tail = new Node(0);
            head.insertNext(tail);
            headAndTail[i][0] = head;
            headAndTail[i][1] = tail;
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

        if (target.next != null) {
            this.next.prev = target;
        }
        this.next = target;
    }

    public void disConnectToRight() {
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
        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        this.prev = null;
        this.next = null;
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
}