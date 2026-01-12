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
            int mainCommand = sc.nextInt();
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            }
            if (mainCommand == 2) {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt()));
            }
            if (mainCommand == 3) {
                commands.add(new Command(mainCommand, sc.nextInt(), sc.nextInt()));
            }
            if (mainCommand == 4) {
                commands.add(new Command(mainCommand, sc.nextInt()));
            }
        }

        new Solver(N, commands).solve();
    }
}

class Solver {
    int maxNodeNumber;
    ArrayList<Command> commands;
    Node[] nodes;

    public Solver(
            int maxNodeNumber,
            ArrayList<Command> commands
    ) {
        this.nodes = new Node[maxNodeNumber + 1];
        this.maxNodeNumber = maxNodeNumber;
        this.commands = commands;
    }

    public void solve() {
        for (int i = 1; i <= maxNodeNumber; i++) {
            nodes[i] = new Node(i);
        }
        for (Command command : commands) {
            int mainCommand = command.mainCommand;
            int i = command.value1;
            int j = command.value2;
            if (mainCommand == 1) {
                nodes[i].pop();
            }
            if (mainCommand == 2) {
                Node cur = nodes[i];
                Node target = nodes[j];
                cur.insertPrev(target);
            }
            if (mainCommand == 3) {
                Node cur = nodes[i];
                Node target = nodes[j];
                cur.insertNext(target);
            }
            if (mainCommand == 4) {
                Node cur = nodes[i];
                cur.print();
            }
        }
        for (int i = 1; i <= maxNodeNumber; i++) {
            Node cur = nodes[i];
            if (cur.next == null) {
                System.out.print(0);
            } else {
                System.out.print(cur.next.value);
            }
            System.out.print(" ");
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

    public void pop() {
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        prev = null;
        next = null;
    }

    public void insertPrev(Node target) {
        target.next = this;
        target.prev = this.prev;

        if (this.prev != null) {
            this.prev.next = target;
        }
        this.prev = target;
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
        if (prev == null) {
            System.out.print(0);
        } else {
            System.out.print(prev.value);
        }
        System.out.print(" ");
        if (next == null) {
            System.out.print(0);
        } else {
            System.out.print(next.value);
        }
        System.out.println();
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