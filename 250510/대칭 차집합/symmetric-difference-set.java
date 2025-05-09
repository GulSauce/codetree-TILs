import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int aCount, bCount;
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        aCount = sc.nextInt();
        bCount = sc.nextInt();
        for (int i = 0; i < aCount; i++) {
            A.add(sc.nextInt());
        }
        for (int i = 0; i < bCount; i++) {
            B.add(sc.nextInt());
        }
        sc.close();
        
        new Solver(A,B).solve();
    }
}

class Solver {
    ArrayList<Integer> A;
    ArrayList<Integer> B;

    public Solver(
            ArrayList<Integer> A,
            ArrayList<Integer> B
    ) {
        this.A = A;
        this.B = B;
    }

    public void solve() {
        HashSet<Integer> aNumbersMapper = new HashSet<>(A);
        HashSet<Integer> bNumbersMapper = new HashSet<>(B);

        for (Integer aNumber : A) {
            bNumbersMapper.remove(aNumber);
        }
        for (Integer bNumber : B) {
            aNumbersMapper.remove(bNumber);
        }

        HashSet<Integer> unionMapper = new HashSet<>();
        for (Integer aNumber : aNumbersMapper) {
            unionMapper.add(aNumber);
        }
        for (Integer bNumber : bNumbersMapper) {
            unionMapper.add(bNumber);
        }
        System.out.println(unionMapper.size());
    }
}