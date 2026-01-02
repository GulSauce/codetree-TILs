import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a = sc.nextInt();

        int curNumber= 1;
        while(curNumber <= N){
            System.out.println(curNumber%a == 0 ? 1: 0);
            curNumber++;
        }
    }
}