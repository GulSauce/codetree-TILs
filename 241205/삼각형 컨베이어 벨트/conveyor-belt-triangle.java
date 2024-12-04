import java.util.*;

public class Main {
    private static class Solver {
        Queue<Integer>[] conveyor;
        int t;

        public  Solver(
               Queue<Integer>[] conveyor,
                int t
        ){
            this.conveyor = conveyor;
            this.t = t;
        }

        public void  solve(){
            for(int i = 0; i < t; i++){
                moveConveyor();
            }

            printConveyor();
        }

        private void printConveyor(){
            printQueueReverse(conveyor[0]);
            printQueueReverse(conveyor[1]);
            printQueueReverse(conveyor[2]);
        }

        private void printQueueReverse(Queue<Integer> q){
            Stack<Integer>printStack = new Stack<>();
            while (!q.isEmpty()){
                printStack.add(q.poll());
            }
            while (!printStack.isEmpty()){
                System.out.printf("%d ", printStack.pop());
            }
            System.out.println();
        }

        private void moveConveyor(){
            int conveyor0PopValue = conveyor[0].poll();
            int conveyor1PopValue = conveyor[1].poll();
            int conveyor2PopValue = conveyor[2].poll();
            conveyor[0].add(conveyor2PopValue);
            conveyor[1].add(conveyor0PopValue);
            conveyor[2].add(conveyor1PopValue);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int t = sc.nextInt();

        int[][] conveyorValue = new int[3][n];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < n; j++){
                conveyorValue[i][j] = sc.nextInt();
            }
        }

        Queue<Integer>[] conveyor = new Queue[3];
        for(int i = 0; i < conveyor.length; i++){
            conveyor[i] = new LinkedList<>();
        }


        for(int i = n-1; i >=0; i--){
            conveyor[0].add(conveyorValue[0][i]);
        }
        for(int i = n-1; i >=0; i--){
            conveyor[1].add(conveyorValue[1][i]);
        }
        for(int i = n-1; i >=0; i--){
            conveyor[2].add(conveyorValue[2][i]);
        }

        new Solver(conveyor, t).solve();
    }
}