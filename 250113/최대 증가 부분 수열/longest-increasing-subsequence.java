import java.util.Scanner;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_NUM = 1000;

    public static int n;

    public static int[] a = new int[MAX_NUM + 1];

    // dp[i] :
    // 마지막으로 고른 원소의 위치가 i인
    // 부분 수열 중 최장 부분 수열의 길이
    public static int[] dp = new int[MAX_NUM + 1];

    public static void initialize() {
        // 최대를 구하는 문제이므로, 
        // 초기에는 전부 INT_MIN을 넣어줍니다.
        for(int i = 0; i <= n; i++)
            dp[i] = INT_MIN;
        
        // 0번째 index와 비교했을 때 항상 갱신될 수
        // 있는 값을 넣어줍니다.
        dp[0] = 0;
        a[0] = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        for(int i = 1; i <= n; i++)
            a[i] = sc.nextInt();

        initialize();

        for(int i = 1; i <= n; i++) {
            // i번째 보다 앞에 있는 원소들 중 
            // a[i]보다는 값이 작은 곳을 골라 바로 그 뒤에
            // 새로운 원소인 a[i]를 추가했을 때의 
            // 부분 수열 중 최대 부분 수열의 길이를 계산합니다.
            for(int j = 0; j < i; j++) {
                if(a[j] < a[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        // 마지막 원소의 위치가 i일 때의 부분 수열들 중
        // 가장 길이가 긴 부분 수열을 고릅니다.
        int answer = 0;
        for(int i = 0; i <= n; i++)
            answer = Math.max(answer, dp[i]);
        
        System.out.println(answer);
    }
}