#include <iostream>

using namespace std;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};

int n;
int board[100][100];

bool isInBorder(int x, int y){
    return 0 <= x && x <= n-1 && 0 <= y && y <= n-1;
}

bool checkMatchedCondition(int x, int y){
    int count = 0;
    for(int i = 0; i <= 3; i++){
        int curX = x + dx[i];
        int curY = y + dy[i];
        if(isInBorder(curX, curY) && board[curY][curX] == 1){
            count++;
        }
    }
    if(3 <= count){
        return true;
    }
    return false;
}

int countMatchedCondition(){
    int count = 0;
    for(int y = 0 ; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            if(checkMatchedCondition(x, y)){
                count++;
            }
        }
    }
    return count;
}

int main() {
    cin >> n;
    for(int y = 0 ; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            cin >> board[y][x];
        }
    }

    int count = countMatchedCondition();
    cout << count;
    return 0;
}