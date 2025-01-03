import java.util.*;

public class Main {
    private static class Solver{
        List<Coordinate> points;
        List<Coordinate> selectedPoints = new ArrayList<>();

        int selectCount;
        int minMaxDist = Integer.MAX_VALUE;

        public Solver(
                List<Coordinate> points,
                int selectCount
        ){
            this.points = points;
            this.selectCount = selectCount;
        }

        public void solve(){
            getCombination(0, -1);
            System.out.print(minMaxDist);
        }

        private void getCombination(int curSelectCount, int lastIndex){
            if(curSelectCount == selectCount){
                minMaxDist = Math.min(minMaxDist, getMaxDist());
                return;
            }

            for(int i = lastIndex+1; i < points.size(); i++){
                selectedPoints.add(points.get(i));
                getCombination(curSelectCount+1, i);
                selectedPoints.remove(selectedPoints.size()-1);
            }
        }

        private int getMaxDist(){
            int maxDist = 0;

            for(int i = 0; i < selectedPoints.size(); i++){
                for(int j = 0; j < selectedPoints.size(); j++){
                    int dx = selectedPoints.get(i).x - selectedPoints.get(j).x;
                    int dy = selectedPoints.get(i).y - selectedPoints.get(j).y;
                    int curDist = dx*dx+dy*dy;
                    maxDist = Math.max(maxDist, curDist);
                }
            }
            return maxDist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Coordinate> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            points.add(new Coordinate(sc.nextInt(), sc.nextInt()));
        }
        new Solver(points, m).solve();
    }

    private static class Coordinate{
        int x;
        int y;

        public Coordinate(
                int x,
                int y
        ){
            this.x = x;
            this.y = y;
        }
    }
}