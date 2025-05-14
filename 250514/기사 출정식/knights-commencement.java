import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> knightNumbers = new ArrayList<>();
        ArrayList<Integer> callNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            knightNumbers.add(sc.nextInt());
        }
        for (int i = 0; i < M; i++) {
            callNumbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(knightNumbers, callNumbers).solve();
    }
}

class Solver {
    ArrayList<Integer> knightNumbers;
    ArrayList<Integer> callNumbers;
    HashMap<Integer, Node> numberNodeMapper = new HashMap<>();

    public Solver(
            ArrayList<Integer> knightNumbers,
            ArrayList<Integer> callNumbers
    ) {
        this.knightNumbers = knightNumbers;
        this.callNumbers = callNumbers;
    }

    public void solve() {
        Node first = new Node(knightNumbers.get(0));
        numberNodeMapper.put(first.value, first);
        Node cur = first;
        for (int i = 1; i < knightNumbers.size(); i++) {
            cur.connectToRight(new Node(knightNumbers.get(i)));
            numberNodeMapper.put(cur.next.value, cur.next);
            cur = cur.next;
        }
        cur.connectToRight(first);

        for (Integer callNumber : callNumbers) {
            Node calledKnight = numberNodeMapper.get(callNumber);
            System.out.println(calledKnight.next.value + " " + calledKnight.prev.value);
            calledKnight.pop();
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
        this.next = target;
        target.prev = this;
    }
}