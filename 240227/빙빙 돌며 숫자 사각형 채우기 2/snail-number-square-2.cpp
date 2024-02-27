#include <iostream>

using namespace std;

int n, m;
int board[100][100];

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

bool isInRange(int currentX, int currentY){
    return 0 <= currentX && currentX <= m-1
    && 0 <= currentY && currentY <= n-1;
}

void drawValues(){
    int direcitionIndex = 1;
    int lastValue = n*m;
    
    int currentX = 0;
    int currentY = 0;
    
    for(int i = 1; i <= lastValue; i++){
        board[currentY][currentX] = i;
        
        int testCurrentX =  currentX + dx[direcitionIndex];
        int testCurrentY = currentY + dy[direcitionIndex];
        if(!isInRange(testCurrentX, testCurrentY) || board[testCurrentY][testCurrentX]){
            direcitionIndex = (direcitionIndex - 1 + 4) % 4;
        }
        currentX += dx[direcitionIndex];
        currentY += dy[direcitionIndex];
    }

}

int main() {
    cin >> n >> m;

    drawValues();
    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}