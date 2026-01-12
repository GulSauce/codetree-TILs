import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int Q;
        List<Operation> operations = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        Q = toInt(st);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            operations.add(new Operation(toInt(st), toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, operations).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<Operation> operations;
    Node[] nodes;

    public Solver(int endNodeNumber, List<Operation> operations) {
        this.endNodeNumber = endNodeNumber;
        this.operations = operations;
        this.nodes = new Node[endNodeNumber + 2];
    }

    public void solve() {
        Node head = new Node(0);
        nodes[0] = head;

        for (int i = 1; i <= endNodeNumber; i++) {
            nodes[i] = new Node(i);
            nodes[i - 1].insertToRight(nodes[i]);
        }

        Node tail = new Node(endNodeNumber + 1);
        nodes[endNodeNumber + 1] = tail;
        nodes[endNodeNumber].insertToRight(tail);

        for (Operation operation : operations) {
            Node aPrev = nodes[operation.a].prev;
            Node aStart = nodes[operation.a];
            Node aEnd = nodes[operation.b];
            Node aNext = nodes[operation.b].next;

            Node bPrev = nodes[operation.c].prev;
            Node bStart = nodes[operation.c];
            Node bEnd = nodes[operation.d];
            Node bNext = nodes[operation.d].next;
            // a -> b
            if (nodes[operation.b].next == nodes[operation.c]) {
                aPrev.insertToRight(bStart);
                bEnd.insertToRight(aStart);
                aEnd.insertToRight(bNext);
            }
            // b -> a
            else if (nodes[operation.d].next == nodes[operation.a]) {
                bPrev.insertToRight(aStart);
                aEnd.insertToRight(bStart);
                bEnd.insertToRight(aNext);
            } else {
                aPrev.disConnectFromRight();
                aEnd.disConnectFromRight();

                bPrev.disConnectFromRight();
                bEnd.disConnectFromRight();

                aPrev.insertToRight(bStart);
                bEnd.insertToRight(aNext);

                bPrev.insertToRight(aStart);
                aEnd.insertToRight(bNext);
            }
        }

        Node cur = head.next;
        while (cur != tail) {
            System.out.print(cur.number + " ");
            cur = cur.next;
        }
    }
}

class Node {

    int number;
    Node prev;
    Node next;

    public Node(int number) {
        this.number = number;
    }

    public void disConnectFromRight() {
        if (this.next != null) {
            this.next.prev = null;
        }
        this.next = null;
    }

    public void insertToRight(Node next) {
        if (next != null) {
            next.prev = this;
        }
        this.next = next;
    }
}

class Operation {

    int a;
    int b;
    int c;
    int d;

    public Operation(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}