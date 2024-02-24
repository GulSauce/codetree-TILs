#include <iostream>

using namespace std;

bool board[200][200];

int main() {
    int N; 
    cin >> N;
    
    int offset = 100;
    while(N--){
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        x1 += offset;
        x2 += offset;
        y1 += offset;
        y2 += offset;
        for(int y = y1; y <= y2-1; y++){
            for(int x = x1; x <= x2-1; x++){
                board[y][x] = true;
            }
        }
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