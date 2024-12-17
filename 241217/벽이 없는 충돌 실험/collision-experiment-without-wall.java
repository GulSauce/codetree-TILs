import java.util.Scanner;
import java.util.ArrayList;

class Marble {
    int x, y, weight, dir, num;
    public Marble(int x, int y, int weight, int dir, int num) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.dir = dir;
        this.num = num;
    }
}

public class Main {
    public static final int BLANK = -1;
    public static final int ASCII_NUM = 128;
    public static final int COORD_SIZE = 4000;
    public static final int OFFSET = 2000;
    public static final int DIR_NUM = 4;
    
    // 전역 변수 선언:
    public static int t, n;
    
    public static int[] mapper = new int[ASCII_NUM];
    
    public static int[] dx = new int[]{0, 1, -1, 0};
    public static int[] dy = new int[]{1, 0, 0, -1};
    
    public static int currTime;
    public static int lastCollisionTime;
    
    public static ArrayList<Marble> marbles = new ArrayList<>();
    public static ArrayList<Marble> nextMarbles = new ArrayList<>();
    public static int[][] nextMarbleIndex = new int[COORD_SIZE + 1][COORD_SIZE + 1];
    
    // 해당 구슬이 1초 후에 어디 위치에 있는지를 구해 상태를 반환합니다.
    public static Marble Move(Marble marble) {
        // tuple의 경우 다음과 같이 원하는 변수에 값을 뽑아줄 수 있습니다.
        int x = marble.x;
        int y = marble.y;
        int weight = marble.weight;
        int dir = marble.dir;
        int num = marble.num;
        
        int nx = x + dx[dir], ny = y + dy[dir];
        return new Marble(nx, ny, weight, dir, num);
    }
    
    // 해당 구슬과 충돌이 일어나는 구슬이 있는지 확인합니다.
    // 있다면 해당 구슬의 index를 반환하고, 없다면 BLANK를 반환합니다.
    // 이는 nextMarbleIndex를 활용하면 O(1)에 바로 가능합니다.
    public static int findDuplicateMarble(Marble marble) {
        int targetX = marble.x;
        int targetY = marble.y;
    
        return nextMarbleIndex[targetX][targetY];
    }
    
    // 두 구슬이 같은 위치에서 충돌했을 경우
    // 살아남는 구슬을 반환합니다.
    public static Marble Collide(Marble marble1, Marble marble2) {
        int weight1 = marble1.weight;
        int num1 = marble1.num;
    
        int weight2 = marble2.weight;
        int num2 = marble2.num;
    
        // 첫 번째 구슬을 따라가게 되는 경우는
        // 첫 번째 구슬의 무게가 더 크거나
        // 무게는 같은데 번호가 더 클 경우 입니다.
        if(weight1 > weight2 || (weight1 == weight2 && num1 > num2))
            return marble1;
        else
            return marble2;
    }
    
    // 구슬이 이미 (0, 0) ~ (COORD_SIZE, COORD_SIZE) 사이를 벗어났다면
    // 더 이상 충돌이 일어나지 않으므로
    // Active Coordinate를 벗어났다고 판단합니다.
    public static boolean outOfActiveCoordinate(Marble marble) {
        int x = marble.x;
        int y = marble.y;
    
        return x < 0 || x > COORD_SIZE || y < 0 || y > COORD_SIZE;
    }
    
    // 그 다음 구슬의 목록에 반영합니다.
    public static void pushNextMarble(Marble marble) {
        // 구슬이 이미 (0, 0) ~ (COORD_SIZE, COORD_SIZE) 사이를 벗어났다면
        // 그 이후부터는 절대 충돌이 일어나지 않으므로
        // 그 구슬은 앞으로 더 이상 관찰하지 않습니다. 
        if(outOfActiveCoordinate(marble))
            return;
    
        int index = findDuplicateMarble(marble);
    
        // Case1 : 같은 위치에 있는 구슬이 아직 없다면 그대로 목록에 추가합니다.
        if(index == BLANK) {
            nextMarbles.add(marble);
    
            // 나중에 위치가 겹치는 구슬이 있는지,
            // 만약 있다면 그 구슬이 nextMarbles의 어느 index에 있는지를
            // 상수 시간안에 판단하기 위해 해당 위치에
            // 새로 추가되는 구슬의 index를 적어놓습니다.
            int x = marble.x;
            int y = marble.y;
            nextMarbleIndex[x][y] = (int) (nextMarbles.size()) - 1;
        }
    
        // Case2 :
        // 다음 구슬의 목록 중 같은 위치에 구슬이 이미 있다면
        // 더 영향력 있는 구슬만 남기고
        // 현재 시간을 가장 최근 충돌 시간에 기록합니다.
        else {
            Marble newMarble = Collide(nextMarbles.get(index), marble);
            nextMarbles.set(index, newMarble);
            lastCollisionTime = currTime;
        }
    }
    
    // 모든 구슬들을 한 칸씩 움직이는 시뮬레이션을 진행합니다.
    public static void simulate() {
        for(int i = 0; i < (int) marbles.size(); i++) {
            // Step1 : 각 구슬에 대해 한 칸 움직인 이후의 위치를 받아옵니다.
            Marble nextMarble = Move(marbles.get(i));
    
            // Step2 : 그 다음 구슬의 목록에 반영합니다.
            pushNextMarble(nextMarble);
        }
        
        marbles = (ArrayList<Marble>) nextMarbles.clone();
    
        // 그 다음 Simulation 때 다시 사용해야하므로
        // 충돌여부를 빠르게 판단하기 위해 쓰였던 nextMarbleIndex 배열과
        // 다음 구슬의 목록을 기록했던 nextMarbles를 미리 초기화해줍니다. 
    
        for(int i = 0; i < (int) nextMarbles.size(); i++) {
            int x = nextMarbles.get(i).x;
            int y = nextMarbles.get(i).y;
            nextMarbleIndex[x][y] = BLANK;
        }
        nextMarbles = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;

        // 테스트 케이스 수 입력:
        t = sc.nextInt();

        // 처음에는 구슬이 전혀 놓여있지 않다는 표시로 전부 BLANK로 채워 놓습니다.
        for(int i = 0; i <= COORD_SIZE; i++)
             for(int j = 0; j <= COORD_SIZE; j++)
                nextMarbleIndex[i][j] = BLANK;

        while(t-- > 0) {
            // 새로운 테스트 케이스가 시작될때마다 기존에 사용하던 값들을 초기화해줍니다.
            marbles = new ArrayList<>();
            lastCollisionTime = -1;

            // 입력:
            n = sc.nextInt();
            for(int i = 1; i <= n; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int weight = sc.nextInt();
                char d = sc.next().charAt(0);

                // 구슬이 움직이는 도중에 충돌하는 문제를 깔끔하게 처리하기 위해
                // 좌표를 2배로 불려 1초에 한칸 씩 이동하는 문제로 바꿉니다.
                // 이렇게 문제가 바뀌면 따로 구슬이 움직이는 도중 충돌하는 경우를 생각하지
                // 않아도 됩니다.
                x *= 2; y *= 2;

                // 좌표를 전부 양수로 만들어야 동일한 위치에서 충돌이 일어나는지를
                // 판단하는 데 사용할 nextMarbleIndex 배열에
                // 각 구슬의 위치마다 자신의 index를 저장할 수 있으므로
                // 좌표를 전부 양수로 만듭니다.
                // 입력으로 들어올 수 있는 좌표값 중 가장 작은 값이 -2000 이므로
                // OFFSET을 2000으로 잡아 전부 더해줍니다.
                // 같은 OFFSET을 모든 구슬에 전부 더해주는 것은
                // 답에 전혀 영향을 미치지 않습니다.
                x += OFFSET; y += OFFSET;
                marbles.add(new Marble(x, y, weight, mapper[d], i));
            }

            // OFFSET이 더해진 구슬들의 처음 위치는 전부 
            // (0, 0)에서 (4000, 4000) 사이에 있기 때문에 
            // COORD SIZE + 1(4001)만큼 이동하면
            // 입력으로 주어진 구슬들이 모두 (0, 0) ~ (4000, 4000)
            // 영역 밖으로 벗어나게 되므로 더 이상 충돌이 일어나지 않게 됩니다.
            // 따라서 시뮬레이션을 총 COORD_SIZE번 진행합니다.
            for(int i = 1; i <= COORD_SIZE; i++) {
                currTime = i;
                simulate();
            }
            
            // 출력:
            System.out.println(lastCollisionTime);
        }
    }
}