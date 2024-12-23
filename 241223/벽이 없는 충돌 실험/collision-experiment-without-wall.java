import java.awt.image.TileObserver;
import java.util.*;

public class Main {
    private static class Solver{
        final int TOTAL_TIME = 1000;

        int[] deltaX = {1, 0, -1, 0};
        int[] deltaY = {0, 1, 0, -1};

        int[][] surviveBidIndex = new int[6001][6001];

        List<BidInfo> bidInfos;

        Map<String, Integer> directionIndexMap = new HashMap<>();

        public Solver(
                List<BidInfo> bidInfos
        ){
            this.bidInfos = bidInfos;
        }

        public void solve(){
            initSurviveBidIndex();
            initDirectionIndexMap();
            int elapsedTime = -1;
            for(int time = 1; time <= TOTAL_TIME; time++) {
                moveBids();
                boolean isCollide = removeCollideBidsRemainOne();
                if(isCollide){
                    elapsedTime = time;
                }
            }
            System.out.println(elapsedTime);
        }

        private void initSurviveBidIndex(){
            for(int[] array: surviveBidIndex){
                Arrays.fill(array, -1);
            }
        }

        private void printBidInfos(){
            System.out.println("===============");
            for(BidInfo bidInfo: bidInfos){
                System.out.printf("%d %d\n", bidInfo.x, bidInfo.y);
            }
            System.out.println("===============");
        }

        private void moveBids(){
            for(BidInfo bidInfo: bidInfos){
                int directionIndex = directionIndexMap.get(bidInfo.direction);
                bidInfo.x = bidInfo.x + deltaX[directionIndex];
                bidInfo.y = bidInfo.y + deltaY[directionIndex];
            }
        }

        private boolean removeCollideBidsRemainOne(){
            boolean isCollide = false;
            List<BidInfo> surviveBids = new ArrayList<>();
            for (BidInfo curBid : bidInfos) {
                if (surviveBidIndex[curBid.y][curBid.x] == -1) {
                    surviveBids.add(curBid);
                    surviveBidIndex[curBid.y][curBid.x] = surviveBids.size() - 1;
                    continue;
                }
                isCollide = true;
                int existBidIndex = surviveBidIndex[curBid.y][curBid.x];
                BidInfo existBid = surviveBids.get(existBidIndex);
                if (isExistBidRemain(curBid, existBid)) {
                    continue;
                }
                surviveBids.set(existBidIndex, curBid);
            }
            for (BidInfo curBid : bidInfos) {
               surviveBidIndex[curBid.y][curBid.x] = -1;
            }
            this.bidInfos = surviveBids;
            return isCollide;
        }

        private boolean isExistBidRemain(BidInfo curBid, BidInfo existBid){
            if(curBid.weight > existBid.weight){
                return false;
            }
            if(curBid.weight == existBid.weight){
                if(curBid.number > existBid.number){
                    return false;
                }
            }
            return true;
        }

        private void initDirectionIndexMap(){
            directionIndexMap.put("R", 0);
            directionIndexMap.put("U", 1);
            directionIndexMap.put("L", 2);
            directionIndexMap.put("D", 3);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testCase = 0; testCase < T; testCase++){
            int N = sc.nextInt();
            List<BidInfo> bidInfos = new ArrayList<>();
            for(int number = 1; number <= N; number++){
                bidInfos.add(new BidInfo(number, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next()));
            }
            new Solver(bidInfos).solve();
        }
    }

    private static class BidInfo{
        int number;
        int x;
        int y;
        int weight;
        String direction;

        public BidInfo(
                int number,
                int x,
                int y,
                int w,
                String d
        ){
            this.number = number;
            this.x = 2*x + 3000;
            this.y = 2*y + 3000;
            this.weight = w;
            this.direction = d;
        }
    }
}