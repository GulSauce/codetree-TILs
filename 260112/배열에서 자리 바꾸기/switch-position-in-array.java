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
            nodes[i - 1].connectToRight(nodes[i]);
        }

        Node tail = new Node(endNodeNumber + 1);
        nodes[endNodeNumber + 1] = tail;
        nodes[endNodeNumber].connectToRight(tail);

        for (Operation operation : operations) {
            Node aPrev = nodes[operation.aStart].prev;
            Node aStart = nodes[operation.aStart];
            Node aEnd = nodes[operation.aEnd];
            Node aNext = nodes[operation.aEnd].next;

            Node bPrev = nodes[operation.bStart].prev;
            Node bStart = nodes[operation.bStart];
            Node bEnd = nodes[operation.bEnd];
            Node bNext = nodes[operation.bEnd].next;
            // a -> b
            if (nodes[operation.aEnd].next == nodes[operation.bStart]) {
                aPrev.connectToRight(bStart);
                bEnd.connectToRight(aStart);
                aEnd.connectToRight(bNext);
            }
            // b -> a
            else if (nodes[operation.bEnd].next == nodes[operation.aStart]) {
                bPrev.connectToRight(aStart);
                aEnd.connectToRight(bStart);
                bEnd.connectToRight(aNext);
            } else {
                aPrev.disConnectFromRight();
                aEnd.disConnectFromRight();

                bPrev.disConnectFromRight();
                bEnd.disConnectFromRight();

                aPrev.connectToRight(bStart);
                bEnd.connectToRight(aNext);

                bPrev.connectToRight(aStart);
                aEnd.connectToRight(bNext);
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

    public void connectToRight(Node next) {
        next.prev = this;
        this.next = next;
    }
}

class Operation {

    int aStart;
    int aEnd;
    int bStart;
    int bEnd;

    public Operation(int aStart, int aEnd, int bStart, int bEnd) {
        this.aStart = aStart;
        this.aEnd = aEnd;
        this.bStart = bStart;
        this.bEnd = bEnd;
    }
}