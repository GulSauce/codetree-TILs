import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        BidInfo[] bidInfos;
        int time;
        int maxSaveBid;

        final int MAX_INDEX;

        int[] dr = {0, -1, 0, 1};
        int[] dc = {1, 0, -1, 0};

        public Solver(
                int[][] matrix,
                BidInfo[] bidInfos,
                int t,
                int k
        ){
            this.matrix = matrix;
            this.bidInfos = bidInfos;
            this.time = t;
            this.maxSaveBid = k;
            this.MAX_INDEX = matrix.length-1;
        }

        public void solve(){
            for(int i = 0; i <= time; i++){
                setBids();
                removeCollideBids();
                setNextBids();
            }
            printResult();
        }

        private void printResult(){
            System.out.print(bidInfos.length);
        }

        private void setBids(){
            initMatrix();
            for(BidInfo bidInfo: bidInfos){
                matrix[bidInfo.r][bidInfo.c]++;
            }
        }

        private void removeCollideBids(){
            for(int r = 1; r <= MAX_INDEX; r++){
                for(int c = 1; c <= MAX_INDEX; c++){
                    if(2 <= matrix[r][c]){
                        removeBidByPriority(r, c);
                    }
                }
            }
        }

        private void removeBidByPriority(int r, int c){
            ArrayList<BidInfo> temp = new ArrayList<>();
            ArrayList<BidInfo> removeCandidate = new ArrayList<>();
            for(BidInfo bidInfo: bidInfos){
                if(bidInfo.r != r || bidInfo.c != c){
                    temp.add(bidInfo);
                    continue;
                }
                removeCandidate.add(bidInfo);
            }
            Collections.sort(removeCandidate);
            for(int i = 0; i < maxSaveBid && i < removeCandidate.size(); i++){
                temp.add(removeCandidate.get(i));
            }
            bidInfos = temp.toArray(new BidInfo[0]);
        }

        private void setNextBids(){
            for(BidInfo bidInfo: bidInfos){
                Character direction = bidInfo.moveDirection;
                int directionIndex = getDirectionIndex(direction);

                for(int i = 0; i < bidInfo.v; i++){
                    int nextR = bidInfo.r + dr[directionIndex];
                    int nextC = bidInfo.c + dc[directionIndex];
                    if(isOutOfIndex(nextR) || isOutOfIndex(nextC)){
                        direction = getReverseDirection(direction);
                        directionIndex = getDirectionIndex(direction);
                        nextR = bidInfo.r + dr[directionIndex];
                        nextC = bidInfo.c + dc[directionIndex];
                    }
                    bidInfo.setR(nextR);
                    bidInfo.setC(nextC);
                    bidInfo.setMoveDirection(direction);
                }
            }
        }

        private boolean isOutOfIndex(int index){
            return index <= 0 || MAX_INDEX < index;
        }

        private Character getReverseDirection(Character direction){
            if(direction.equals('U')){
                return 'D';
            }
            if(direction.equals('D')){
                return 'U';
            }
            if(direction.equals('L')){
                return 'R';
            }
            if(direction.equals('R')){
                return 'L';
            }
            throw new RuntimeException("invalid direction input");
        }

        private int getDirectionIndex(Character direction){
            if(direction.equals('R')){
                return 0;
            }
            if(direction.equals('U')){
                return 1;
            }
            if(direction.equals('L')){
                return 2;
            }
            if(direction.equals('D')){
                return 3;
            }
            return -1;
        }

        private void initMatrix(){
            for(int[] array: matrix){
                Arrays.fill(array, 0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] matrix = new int[n+1][n+1];
        for(int[] array: matrix){
            Arrays.fill(array, 0);
        }

        int m = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();

        BidInfo[] bidInfos = new BidInfo[m];
        for(int i = 0; i < m; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            Character d = sc.next().charAt(0);
            int v = sc.nextInt();
            bidInfos[i] = new BidInfo(i, r, c, d, v);
        }

        new Solver(matrix, bidInfos, t, k).solve();
    }

    private static class BidInfo implements Comparable<BidInfo>{
        int number;
        int r;
        int c;
        Character moveDirection;
        int v;

        public BidInfo(
                int number,
                int r,
                int c,
                Character d,
                int v
        ){
            this.number = number;
            this.r = r;
            this.c = c;
            this.moveDirection = d;
            this.v = v;
        }

        public void setR(int r){
            this.r = r;
        }

        public void setC(int c){
            this.c = c;
        }

        public void setMoveDirection(Character moveDirection){
            this.moveDirection = moveDirection;
        }

        @Override
        public int compareTo(BidInfo bidInfo){
            if(this.v == bidInfo.v){
                return bidInfo.number - this.number;
            }
            return bidInfo.v - this.v;
        }
    }
}