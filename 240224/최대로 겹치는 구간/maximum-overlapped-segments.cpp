#include <iostream>
#include <climits>

using namespace std;

int board[200];

int main() {
    int n;
    cin >> n;
    int offSet = 100;
    while(n--){
        int x1, x2;
        cin >> x1 >> x2;
        x1 += offSet;
        x2 += offSet;
        for(int i = x1; i <= x2-1; i++){
            board[i]++;
        }
    }

    int maxSameLine = INT_MIN;
    for(int i = 0; i <= 199; i++){
        maxSameLine = max(maxSameLine, board[i]);
    }

    cout << maxSameLine;
    return 0;
}