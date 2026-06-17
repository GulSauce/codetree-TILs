import java.io.*;
import java.util.*;
public class Main {
  public static void main(String[] a) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(br.readLine());
    int n=Integer.parseInt(st.nextToken()), m=Integer.parseInt(st.nextToken());
    int[] v=new int[n+1];
    st=new StringTokenizer(br.readLine());
    for(int i=1;i<=n;i++) v[i]=Integer.parseInt(st.nextToken());
    final long INF=Long.MAX_VALUE/4;
    long[][][] dp=new long[n+1][n+1][m+1];
    for(long[][] x:dp) for(long[] y:x) Arrays.fill(y,INF);
    for(int i=1;i<=n;i++){ int d=i-1; if(d<=m) dp[i][0][d]=0; }   // base
    for(int i=1;i<=n;i++) for(int j=0;j<i;j++) for(int k=0;k<=m;k++){
      if(dp[i][j][k]>=INF) continue;
      long base=dp[i][j][k];
      for(int p=i+1;p<=n;p++){
        int extra=p-i-1, nk=k+extra; if(nk>m) continue;
        long c=base+Math.abs(v[p]-v[i]);              // p 를 i 사람에게
        if(c<dp[p][j][nk]) dp[p][j][nk]=c;
        long add=(j>=1)?Math.abs(v[p]-v[j]):0;        // p 를 j 사람에게
        long c2=base+add;
        if(c2<dp[p][i][nk]) dp[p][i][nk]=c2;
      }
    }
    long ans=INF;
    for(int i=1;i<=n;i++) for(int j=0;j<i;j++) for(int k=0;k<=m;k++)
      if(k+(n-i)<=m) ans=Math.min(ans,dp[i][j][k]);
    System.out.println(ans);
  }
}