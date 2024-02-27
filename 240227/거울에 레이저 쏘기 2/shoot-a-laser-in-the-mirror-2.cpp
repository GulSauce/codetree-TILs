#include <iostream>
#include <string>

using namespace std;

int N;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int laserDx[4] = {0, -1, 0, 1};
int laserDy[4] = {1, 0, -1, 0};

char board[1000][1000];

class LaserInfo{
    public:
        int directionIndex;
        int x;
        int y;

        LaserInfo(int directionIndex, int x, int y){
            this->directionIndex = directionIndex;
            this->x = x;
            this->y= y;
        }

        LaserInfo(){}
};

LaserInfo getLaserInfo(int startDirection){
    int startX = 0;
    int startY = 0;

    int curDirection = 0;

    for(int i = 2 ; i <= startDirection; i++){
        if((i-1) % N == 0){
            curDirection = (curDirection + 1) % 4;
            continue;
        }
        startX += dx[curDirection];
        startY += dy[curDirection];
    }

    LaserInfo laserInfo = LaserInfo(curDirection, startX, startY);
    return laserInfo;
}

bool isInRange(int y, int x){
    return 0 <= y && y <= N-1 && 0 <= x && x <= N-1;
}

int getReflectionCount(LaserInfo laserInfo){
    int directionIndex = laserInfo.directionIndex;
    int currentY = laserInfo.y;
    int currentX = laserInfo.x;

    int reflectionCount = 0;
    while(true){
        if(isInRange(currentY, currentX) && board[currentY][currentX] != '\0'){
            char mirror = board[currentY][currentX];
            if(mirror == '/'){
                directionIndex = 1 - directionIndex;
                if(directionIndex <= -1){
                    directionIndex += 4;
                }
            }
            if(mirror == '\\'){
                directionIndex = 3 - directionIndex;
            }
            reflectionCount++;
        }
        if(isInRange(currentY, currentX)){
            currentY += laserDy[directionIndex];
            currentX += laserDx[directionIndex];
        }
        if(!isInRange(currentY, currentX)){
            break;
        }
    }

    return reflectionCount;
}

int main() {
    cin >> N;

    for(int y = 0; y <= N-1; y++){
        string str;
        cin >> str;
        for(int x = 0; str[x] != '\0'; x++){
            board[y][x] = str[x];
        }
    }

    int startDirection;
    cin >> startDirection;

    LaserInfo laserInfo = getLaserInfo(startDirection);

    int reflectionCount = getReflectionCount(laserInfo);

    cout << reflectionCount;
    
    return 0;
}