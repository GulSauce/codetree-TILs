#include <iostream>

using namespace std;

bool isComfortable[10000];
bool board[101][101];

int N, M;

int dx[4] = {1, 0, -1 ,0};
int dy[4] = {0, -1, 0 ,1};

bool isInRange(int y, int x){
    return 1 <= y && y <= N && 1 <= x && x <= N;
}

bool checkIsComfortable(int row, int column){
    int coloredCnt = 0;
    for(int i = 0; i <= 3; i++){
        int nextY = row + dy[i];
        int nextX = column + dx[i];
        
        if(!isInRange(nextY, nextX)){
            continue;
        }
        if(board[nextY][nextX]){
            coloredCnt++;
        }
    }

    if(coloredCnt == 3){
        return true;
    }

    return false;
}

int main() {
    cin >> N >> M;
    for(int i = 0; i <= M-1; i++){
        int r, c;
        cin >> r >> c;
        board[r][c] = true;
        if(checkIsComfortable(r, c)){
            isComfortable[i] = true;
        }
    }
    for(int i = 0; i <= M-1; i++){
        if(isComfortable[i]){
            cout << 1;
        }else{
            cout << 0;
        }
        cout << '\n';
    }
    return 0;
}