import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String initString;
        int N;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        initString = sc.next();
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 1) {
                commands.add(new Command(mainCommand, sc.next()));
            } else if (mainCommand == 2) {
                commands.add(new Command(mainCommand, sc.next()));
            } else {
                commands.add(new Command(mainCommand));
            }
        }
        sc.close();

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
        Node cur = new Node(initString);
        for (Command command : commands) {
            int mainCommand = command.mainCommand;
            if (mainCommand == 1) {
                Node newNode = new Node(command.value);
                cur.insertPrev(newNode);
                cur.print();
                continue;
            }
            if (mainCommand == 2) {
                Node newNode = new Node(command.value);
                cur.insertNext(newNode);
                cur.print();
                continue;
            }
            if (mainCommand == 3) {
                if (cur.prev != null) {
                    cur = cur.prev;
                }
                cur.print();
                continue;
            }
            if (mainCommand == 4) {
                if (cur.next != null) {
                    cur = cur.next;
                }
                cur.print();
                cur = cur.next;
            }
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

    public void insertNext(Node target) {
        target.prev = this;
        target.next = this.next;

        this.next = target;
    }

    public void insertPrev(Node target) {
        target.next = this;
        target.prev = prev;

        this.prev = target;
    }

    public void print() {
        if (this.prev == null) {
            System.out.print("(Null)");
        } else {
            System.out.print(prev.value);
        }
        System.out.print(" ");
        System.out.print(value);
        System.out.print(" ");
        if (this.next == null) {
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
