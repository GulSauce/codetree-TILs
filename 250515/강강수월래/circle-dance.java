import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M, Q;
        ArrayList<ArrayList<Integer>> circles = new ArrayList<>();
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < M; i++) {
            ArrayList<Integer> circle = new ArrayList<>();
            int totalCount = sc.nextInt();
            for (int j = 0; j < totalCount; j++) {
                circle.add(sc.nextInt());
            }
            circles.add(circle);
        }
        for (int i = 0; i < Q; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            } else {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt()));
            }
        }
        sc.close();

        new Solver(circles, commands).solve();
    }
}

class Solver {
    ArrayList<ArrayList<Integer>> circles;
    ArrayList<Command> commands;
    HashMap<Integer, Node> numberNodeMapper = new HashMap<>();

    public Solver(
            ArrayList<ArrayList<Integer>> circles,
            ArrayList<Command> commands
    ) {
        this.circles = circles;
        this.commands = commands;
    }

    public void solve() {
        init();
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node a = numberNodeMapper.get(command.value1);
                Node aNext = a.next;
                a.disconnectToRight();

                Node b = numberNodeMapper.get(command.value2);
                Node bPrev = b.prev;
                bPrev.disconnectToRight();

                a.connectToRight(b);
                bPrev.connectToRight(aNext);
            }
            if (command.mainCommand == 2) {
                Node a = numberNodeMapper.get(command.value1);
                Node b = numberNodeMapper.get(command.value2);
                Node aPrev = a.prev;
                Node bPrev = b.prev;

                aPrev.disconnectToRight();
                bPrev.disconnectToRight();
                aPrev.connectToRight(b);
                bPrev.connectToRight(a);
            }
            if (command.mainCommand == 3) {
                Node first = numberNodeMapper.get(command.value1);
                int minValue = first.value;
                Node cur = first.prev;
                while (cur != first) {
                    minValue = Math.min(minValue, cur.value);
                    Node next = cur.prev;
                    cur = next;
                }

                printCounterClockWiseFrom(numberNodeMapper.get(minValue));
            }
        }
    }

    private void printCounterClockWiseFrom(Node target) {
        System.out.print(target.value + " ");
        Node cur = target.prev;
        while (cur != target) {
            System.out.print(cur.value + " ");
            Node next = cur.prev;
            cur = next;
        }
    }

    private void init() {
        for (ArrayList<Integer> circle : circles) {
            Node first = new Node(circle.get(0));
            numberNodeMapper.put(first.value, first);
            Node cur = first;
            for (int i = 1; i < circle.size(); i++) {
                cur.connectToRight(new Node(circle.get(i)));
                numberNodeMapper.put(cur.next.value, cur.next);
                cur = cur.next;
            }
            cur.connectToRight(first);
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

    public void disconnectToRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void connectToRight(Node target) {
        target.prev = this;
        this.next = target;
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