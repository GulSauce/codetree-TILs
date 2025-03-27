import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;
    public static int n;
    public static String N;
    public static long[] pt = new long[100005]; // 10의 거듭제곱 배열
    public static long[][][] dp = new long[100005][3][2]; // DP 배열
    public static int[] a = new int[100005]; // N의 각 자리 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.next();
        n = N.length();
        
        // N의 각 자리를 배열에 저장
        for (int i = 0; i < n; i++) {
            a[i] = N.charAt(i) - '0';
        }
        
        // 10의 거듭제곱 사전 계산
        pt[0] = 1;
        for (int i = 1; i <= n; i++) {
            pt[i] = pt[i - 1] * 10 % MOD;
        }
        
        long ans = 0;
        dp[0][0][1] = 1; // 초기 상태 설정
        
        // 탭뮬레이션 DP
        for (int pos = 0; pos < n; pos++) {
            for (int sum_mod_3 = 0; sum_mod_3 < 3; sum_mod_3++) {
                for (int tight = 0; tight < 2; tight++) {
                    if (dp[pos][sum_mod_3][tight] == 0) continue;
                    int upper = (tight == 1) ? a[pos] : 9;
                    for (int digit = 0; digit <= upper; digit++) {
                        if (digit == 3 || digit == 6 || digit == 9) {
                            // 3, 6, 9가 등장하면 조건 만족, 남은 자리 수 고려
                            long remaining;
                            if (tight == 1) {
                                if (pos + 1 < n) {
                                    String remStr = N.substring(pos + 1);
                                    remaining = Long.parseLong(remStr) + 1;
                                } else {
                                    remaining = 1;
                                }
                            } else {
                                remaining = pt[n - pos - 1];
                            }
                            ans = (ans + dp[pos][sum_mod_3][tight] * remaining % MOD) % MOD;
                        } else {
                            // 3, 6, 9가 아니면 DP 전이
                            int new_sum_mod_3 = (sum_mod_3 + digit) % 3;
                            int new_tight = (tight == 1 && digit == a[pos]) ? 1 : 0;
                            dp[pos + 1][new_sum_mod_3][new_tight] = 
                                (dp[pos + 1][new_sum_mod_3][new_tight] + 
                                 dp[pos][sum_mod_3][tight]) % MOD;
                        }
                    }
                }
            }
        }
        
        // 자리 수 합이 3의 배수인 경우 추가
        for (int tight = 0; tight < 2; tight++) {
            ans = (ans + dp[n][0][tight]) % MOD;
        }
        
        System.out.println(ans-1);
    }
}