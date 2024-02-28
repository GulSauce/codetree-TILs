#include <iostream>

using namespace std;

char board[15][15];

int main() {
    int R, C;
    cin >> R >> C;
    
    for(int y = 0; y <= R-1; y++){
        for(int x = 0; x <= C-1; x++){
            cin >> board[y][x];
        }
    }

    char startChar = board[0][0];
    char endChar = board[R-1][C-1];
    
    if(startChar == endChar){
        cout << 0;
        return 0;
    }

    int ans = 0;
    for(int t1 = 1; t1 <= R-2; t1++){
        for(int t1Index = 0; t1Index <= C-2; t1Index++){
            for(int t2 = t1+1; t2 <= R-2; t2++){
                for(int t2Index = t1Index+1; t2Index <= C-2; t2Index++){
                    if(board[t1][t1Index] != startChar &&
                    board[t2][t2Index] == startChar){
                        ans++;
                    }
                }
            }
        }
    }

    cout << ans;
    return 0;
}