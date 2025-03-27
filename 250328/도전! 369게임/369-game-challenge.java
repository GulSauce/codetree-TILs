import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;
    public static int n;
    public static String N;
    public static long[][][][] dp = new long[100005][3][2][2]; // 최대 100,000자리 + 여유분
    public static int[] a = new int[100005]; // N의 각 자리 수 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.next();
        n = N.length();
        
        // N의 각 자리를 배열에 저장
        for (int i = 0; i < n; i++) {
            a[i] = N.charAt(i) - '0';
        }
        
        // 초기 상태 설정
        dp[0][0][1][0] = 1;
        
        // 자릿수 DP
        for (int pos = 0; pos < n; pos++) {
            for (int sum_mod_3 = 0; sum_mod_3 < 3; sum_mod_3++) {
                for (int tight = 0; tight < 2; tight++) {
                    for (int has_369 = 0; has_369 < 2; has_369++) {
                        if (dp[pos][sum_mod_3][tight][has_369] == 0) continue;
                        int upper = (tight == 1) ? a[pos] : 9;
                        for (int digit = 0; digit <= upper; digit++) {
                            int new_sum_mod_3 = (sum_mod_3 + digit) % 3;
                            int new_tight = (tight == 1 && digit == a[pos]) ? 1 : 0;
                            int new_has_369 = has_369 | (digit == 3 || digit == 6 || digit == 9 ? 1 : 0);
                            dp[pos + 1][new_sum_mod_3][new_tight][new_has_369] = 
                                (dp[pos + 1][new_sum_mod_3][new_tight][new_has_369] + 
                                 dp[pos][sum_mod_3][tight][has_369]) % MOD;
                        }
                    }
                }
            }
        }
        
        // 최종 답 계산
        long ans = 0;
        for (int sum_mod_3 = 0; sum_mod_3 < 3; sum_mod_3++) {
            for (int tight = 0; tight < 2; tight++) {
                for (int has_369 = 0; has_369 < 2; has_369++) {
                    if (has_369 == 1 || sum_mod_3 == 0) {
                        ans = (ans + dp[n][sum_mod_3][tight][has_369]) % MOD;
                    }
                }
            }
        }
        
        System.out.println(ans-1);
    }
}