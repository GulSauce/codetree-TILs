#include <iostream>

using namespace std;

bool board[200][200];

void fetchPaper(int leftMostY, int leftMostX){
    for(int y = leftMostY; y <= leftMostY+7; y++){
        for(int x = leftMostX; x <= leftMostX+7; x++){
            board[y][x] = true;
        }
    }
} 

int main() {
    int N;
    cin >> N;
    int offset = 100;
    while(N--){
        int leftMostX, leftMostY;
        cin >>leftMostX >> leftMostY;
        leftMostX += offset;
        leftMostY += offset;
        fetchPaper(leftMostY, leftMostX);
    }

    int area = 0;
    for(int y = 0; y <= 199; y++){
        for(int x = 0; x <= 199; x++){
            if(board[y][x]){
                area++;
            }
        }
    }
    cout << area;
    return 0;
}