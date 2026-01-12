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
    Node[][] headAndTails;

    public Solver(
        int N,
        int K,
        ArrayList<Command> commands
    ) {
        this.bookCount = N;
        this.shelfCount = K;
        this.commands = commands;
        this.headAndTails = new Node[K + 1][2];
    }


    public void solve() {
        for (int i = 1; i <= shelfCount; i++) {
            headAndTails[i][0] = new Node(-1);
            headAndTails[i][1] = new Node(-1);
            headAndTails[i][0].connectToRight(headAndTails[i][1]);
        }

        Node initPrev = headAndTails[1][0];
        initPrev.disconnectFromRight();
        for (int v = 1; v <= bookCount; v++) {
            Node cur = new Node(v);
            initPrev.connectToRight(cur);
            initPrev = cur;
        }
        initPrev.connectToRight(headAndTails[1][1]);

        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node head = headAndTails[command.value1][0];
                Node target = head.next;
                if (target.value == -1) {
                    continue;
                }
                Node targetNext = target.next;
                head.disconnectFromRight();
                target.disconnectFromRight();
                head.connectToRight(targetNext);

                Node secondTail = headAndTails[command.value2][1];
                Node secondBook = secondTail.prev;
                secondBook.disconnectFromRight();
                secondBook.connectToRight(target);
                target.connectToRight(secondTail);
            }
            if (command.mainCommand == 2) {
                Node tail = headAndTails[command.value1][1];
                Node target = tail.prev;
                if (target.value == -1) {
                    continue;
                }
                Node prev = target.prev;
                prev.disconnectFromRight();
                target.disconnectFromRight();
                prev.connectToRight(tail);

                Node secondHead = headAndTails[command.value2][0];
                Node secondNext = secondHead.next;
                secondHead.disconnectFromRight();
                secondHead.connectToRight(target);
                target.connectToRight(secondNext);
            }
            if (command.mainCommand == 3) {
                Node head = headAndTails[command.value1][0];
                Node tail = headAndTails[command.value1][1];

                Node start = head.next;
                Node end = tail.prev;

                if (start.value == -1 || end.value == -1) {
                    continue;
                }

                head.disconnectFromRight();
                end.disconnectFromRight();
                head.connectToRight(tail);

                Node secondHead = headAndTails[command.value2][0];
                Node secondStart = secondHead.next;
                secondHead.disconnectFromRight();
                secondHead.connectToRight(start);
                end.connectToRight(secondStart);
            }
            if (command.mainCommand == 4) {
                Node head = headAndTails[command.value1][0];
                Node tail = headAndTails[command.value1][1];

                Node start = head.next;
                Node end = tail.prev;

                if (start.value == -1 || end.value == -1) {
                    continue;
                }

                head.disconnectFromRight();
                end.disconnectFromRight();
                head.connectToRight(tail);

                Node secondTail = headAndTails[command.value2][1];
                Node secondEnd = secondTail.prev;
                secondEnd.disconnectFromRight();
                secondEnd.connectToRight(start);
                end.connectToRight(secondTail);
            }
        }

        for (int i = 1; i <= shelfCount; i++) {
            Node cur = headAndTails[i][0].next;
            int count = 0;
            Queue<Node> answer = new LinkedList<>();
            while (cur.value != -1) {
                answer.add(cur);
                count++;
                cur = cur.next;
            }
            System.out.print(count + " ");
            while (!answer.isEmpty()) {
                System.out.print(answer.poll().value + " ");
            }
            System.out.println();
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
            this.next.prev = null;
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
        int value1,
        int value2
    ) {
        this.mainCommand = mainCommand;
        this.value1 = value1;
        this.value2 = value2;
    }
}