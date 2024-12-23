import java.util.*;

public class Main {
    private static class Solver{
        ArrayList<BidInfo> bidInfos;

        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        HashMap<String, ArrayList<BidInfo>> bidExistInfo = new HashMap<>();

        public  Solver(
                ArrayList<BidInfo> bidInfos
        ){
            this.bidInfos = bidInfos;
        }

        public  void solve(){
            int lastCollideTime = -1;
            for(int t = 1; t <= 10000; t++){
                moveBidHalfWithHashing();
                boolean isRemoved = removeCollideBid();
                if(isRemoved){
                    lastCollideTime = t;
                }
            }
            System.out.println(lastCollideTime);
        }

        private boolean removeCollideBid(){
            boolean isRemoved = false;
            ArrayList<BidInfo> savedBids = new ArrayList<>();
            for(ArrayList<BidInfo> bidInfosByKey: bidExistInfo.values()){
                if(bidInfosByKey.size() == 1){
                    savedBids.addAll(bidInfosByKey);
                    continue;
                }
                Collections.sort(bidInfosByKey);
                BidInfo savedBid = bidInfosByKey.get(0);
                savedBids.add(savedBid);
                isRemoved = true;
            }
            bidInfos = savedBids;
            return isRemoved;
        }

        private void moveBidHalfWithHashing(){
            bidExistInfo.clear();
            for(BidInfo bidInfo: bidInfos){
                int directionIndex = getDirectionIndex(bidInfo.direction);
                bidInfo.x = bidInfo.x + 0.5*dx[directionIndex];
                bidInfo.y = bidInfo.y + 0.5*dy[directionIndex];
                String key = bidInfo.x+","+ bidInfo.y;
                if(!bidExistInfo.containsKey(key)){
                    ArrayList<BidInfo> bidInfosByKey = new ArrayList<>();
                    bidInfosByKey.add(bidInfo);
                    bidExistInfo.put(key, bidInfosByKey);
                    continue;
                }
                ArrayList<BidInfo> bidInfosByKey = bidExistInfo.get(key);
                bidInfosByKey.add(bidInfo);
            }
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
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int testCase = 0; testCase < T; testCase++){
            int N = sc.nextInt();
            ArrayList<BidInfo> bidInfos = new ArrayList<>();

            for(int i = 0; i < N; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int w = sc.nextInt();
                Character d = sc.next().charAt(0);
                bidInfos.add(new BidInfo(i, x, y, w, d));
            }

            new Solver(bidInfos).solve();
        }
    }

    private static class BidInfo implements Comparable<BidInfo>{
        int number;
        double x;
        double y;
        int weight;
        Character direction;

        public BidInfo(
                int number,
                int x,
                int y,
                int w,
                Character d
        ){
            this.number = number;
            this.x = x;
            this.y = y;
            this.weight = w;
            this.direction = d;
        }

        @Override
        public int compareTo(BidInfo bidInfo){
            if(this.weight == bidInfo.weight){
                return bidInfo.number - this.number;
            }
            return bidInfo.weight - this.weight;
        }
    }
}