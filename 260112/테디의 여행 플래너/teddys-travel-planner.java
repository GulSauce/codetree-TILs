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
        Node initPrev = first;
        for (int i = 1; i < cities.size(); i++) {
            Node cur = new Node(cities.get(i));
            initPrev.connectToRight(cur);
            initPrev = cur;
        }
        initPrev.connectToRight(first);

        Node pin = first;
        for (Command command : commands) {
            if (command.mainCommand == 1) {
                Node next = pin.next;
                if (next == pin) {
                    continue;
                }
                pin = next;
            } else if (command.mainCommand == 2) {
                Node prev = pin.prev;
                if (prev == pin) {
                    continue;
                }
                pin = prev;
            } else if (command.mainCommand == 3) {
                Node next = pin.next;
                if (next == pin) {
                    continue;
                }
                Node nextNext = next.next;
                next.disconnectFromRight();
                pin.disconnectFromRight();
                pin.connectToRight(nextNext);
            } else if (command.mainCommand == 4) {
                Node newNode = new Node(command.value);
                Node next = pin.next;
                pin.disconnectFromRight();
                pin.connectToRight(newNode);
                newNode.connectToRight(next);
            }
            Node prev = pin.prev;
            Node next = pin.next;

            if (prev.equals(next)) {
                System.out.println(-1);
            } else if (prev == pin || next == pin) {
                System.out.println(-1);
            } else {
                System.out.println(prev.value + " " + next.value);
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

    public void connectToRight(Node target) {
        target.prev = this;
        this.next = target;
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