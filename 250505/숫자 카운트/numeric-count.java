import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<AskedInfo> askedInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            askedInfos.add(new AskedInfo(sc.next(), sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Solver(askedInfos).solve();
    }
}

class Solver {
    int askedInfosIndex;
    List<AskedInfo> askedInfos;
    List<String> candidateNumbers = new ArrayList<>();

    public Solver(
            List<AskedInfo> askedInfos
    ) {
        this.askedInfosIndex = askedInfos.size() - 1;
        this.askedInfos = askedInfos;
    }


    private void initCandidateNumbers() {
        for (char i = '1'; i <= '9'; i++) {
            for (char j = '1'; j <= '9'; j++) {
                for (char k = '1'; k <= '9'; k++) {
                    candidateNumbers.add(new String(new char[]{i, j, k}));
                }
            }
        }
    }

    public void solve() {
        initCandidateNumbers();
        for (AskedInfo askedInfo : askedInfos) {
            List<String> nextCandidate = new ArrayList<>();
            for (String candidateNumber : candidateNumbers) {
                if (!askedInfo.isValid(candidateNumber)) {
                    continue;
                }
                nextCandidate.add(candidateNumber);
            }
            candidateNumbers = nextCandidate;
        }
        System.out.println(candidateNumbers.size());
    }
}

class AskedInfo {
    String saidNumber;
    int firstCount;
    int secondCount;


    public AskedInfo(
            String saidNumber,
            int firstCount,
            int secondCount
    ) {
        this.saidNumber = saidNumber;
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    public boolean isValid(String target) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (saidNumber.charAt(i) != target.charAt(j)) {
                    continue;
                }
                if (i == j) {
                    strike++;
                }
                if (i != j) {
                    ball++;
                }
            }
        }
        return strike == firstCount && ball == secondCount;
    }
}