import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, Q;
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            cities.add(sc.next());
        }
        for (int i = 0; i < Q; i++) {
            int mainCommand = sc.nextInt();
            if (mainCommand == 4) {
                commands.add(new Command(mainCommand, sc.next()));
                continue;
            }

            commands.add(new Command(mainCommand));
        }
        sc.close();

        new Solver(cities, commands).solve();
    }
}


class Solver {
    ArrayList<String> cities;
    ArrayList<Command> commands;

    public Solver(
            ArrayList<String> cities,
            ArrayList<Command> commands
    ) {
        this.cities = cities;
        this.commands = commands;
    }

    public void solve() {
        Node first = new Node(cities.get(0));
        Node cur = first;
        for (int i = 1; i < cities.size(); i++) {
            cur.connectToRight(new Node(cities.get(i)));
            cur = cur.next;
        }
        cur.connectToRight(first);
        cur = cur.next;

        for (Command command : commands) {
            if (command.mainCommand == 1) {
                if (cur.next != null) {
                    cur = cur.next;
                }
            }
            if (command.mainCommand == 2) {
                if (cur.prev != null) {
                    cur = cur.prev;
                }
            }
            if (command.mainCommand == 3) {
                if (cur.next != null) {
                    cur.next.pop();
                }
            }
            if (command.mainCommand == 4) {
                cur.insertToRight(new Node(command.value));
            }
            if (cur.prev == null || cur.next == null) {
                System.out.println(-1);
                continue;
            }
            if (cur.prev.value.equals(cur.next.value)) {
                System.out.println(-1);
                continue;
            }
            System.out.println(cur.prev.value + " " + cur.next.value);
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

    public void connectToRight(Node target) {
        target.prev = this;
        this.next = target;
    }

    public void insertToRight(Node target) {
        target.prev = this;
        target.next = this.next;

        if (this.next != null) {
            this.next.prev = target;
        }
        this.next = target;
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