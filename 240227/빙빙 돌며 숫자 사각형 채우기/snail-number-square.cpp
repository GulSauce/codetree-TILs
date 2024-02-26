#include <iostream>

using namespace std;

int n, m;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int board[100][100];

bool chekcIsNextIsWall(int currentDirection, int currentX , int currentY){
    currentX += dx[currentDirection];
    currentY += dy[currentDirection];
    return currentX <= -1 || m <= currentX
    || currentY <= -1 || n <= currentY
    || 1 <= board[currentY][currentX];
}

int main() {
    cin >> n >> m;

    int currentX = 0;
    int currentY = 0;

    int cnt = n*m;

    int currentDirection = 0;
    for(int value = 1; value <= cnt; value++){
        board[currentY][currentX] = value;

        if(chekcIsNextIsWall(currentDirection, currentX, currentY)){
            currentDirection = (currentDirection+1)%4;
        }

        currentX += dx[currentDirection];
        currentY += dy[currentDirection];
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}