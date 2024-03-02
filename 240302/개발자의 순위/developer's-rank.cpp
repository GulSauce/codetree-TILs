#include <iostream>

using namespace std;

int K, N;

int board[10][20];
int ascendCount[21];

bool checkIsAscend(int currentDevNo, int targetDevNo){
    for(int y = 0; y <= K-1; y++){
        int currentDevNoRankingInThisRow = 0;
        int targetDevNoRankingInThisRow = 0;
        for(int x = 0; x <= N-1; x++){
            if(board[y][x] == currentDevNo){
                currentDevNoRankingInThisRow = x;
            }
            if(board[y][x] == targetDevNo){
                targetDevNoRankingInThisRow = x;
            }
        }
        if(targetDevNoRankingInThisRow + 1 <= currentDevNoRankingInThisRow){
            return false;
        }
    }
    return true;
}

int main() {
    cin >> K >> N;
    for(int y = 0; y <= K-1; y++){
        for(int x = 0; x <= N-1; x++){
            cin >> board[y][x];
        }
    }
    
    for(int currentDevNo = 1; currentDevNo <= N; currentDevNo++){
        for(int targetDevNo = 1; targetDevNo <= N; targetDevNo++){
            if(currentDevNo == targetDevNo){
                continue;
            }
            if(checkIsAscend(currentDevNo, targetDevNo)){
                ascendCount[currentDevNo]++;
            }
        }
    }

    int ans = 0;
    for(int currentDevNo = 1; currentDevNo <= N; currentDevNo++){
        ans += ascendCount[currentDevNo];
    }

    cout << ans;
    return 0;
}