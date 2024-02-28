#include <climits>
#include <iostream>

using namespace std;

int board[20][20];

int main() {
    int N;
    cin >> N;

    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= N-1; x++){
            cin >> board[y][x];
        }
    }

    int ans = INT_MIN;

    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= N-3; x++){
            int curCointCount = 0;
            for(int i = 0; i <= 2; i++){
                if(board[y][x+i] == 1){
                    curCointCount++;
                }
            }
            ans = max(ans, curCointCount);
        }
    }

    cout << ans;
    return 0;
}