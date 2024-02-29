#include <iostream>

using namespace std;

int N;
int board[20][20];

int getCurrentCoinCount(int x1, int y1, int x2, int y2){
    int currentCointCount = 0;

    for(int i = 0; i <= 2; i++){
        currentCointCount += board[y1][x1+i];
    }

    for(int i = 0; i <= 2; i++){
        currentCointCount += board[y2][x2+i];
    }

    return currentCointCount;
}

bool isInRange(int x, int y){
    return 0 <= x && x <= N-1 && 0 <= y && y <= N-1;
}

int main() {
    cin >> N;

    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= N-1; x++){
            cin >> board[y][x];
        }
    }  

    int maxCointCount = 0;

    for(int firstY = 0; firstY <= N-1; firstY++){
        for(int firstX = 0; firstX <= N-1; firstX++){
            for(int secondY = firstY; secondY <= N-1; secondY++){
                int secondYStart = 0;
                if(firstY == secondY){
                    secondYStart = firstX + 3;
                }
                for(int secondX = secondYStart; secondX <= N-1; secondX++){
                    if(!isInRange(firstX, firstY) || !isInRange(secondX, secondY)){
                        continue;
                    }
                    int currentCointCount = getCurrentCoinCount(firstX, firstY, secondX, secondY);
                    maxCointCount = max(maxCointCount, currentCointCount);
                }
            }
        }
    }

    cout << maxCointCount;
    return 0;
}