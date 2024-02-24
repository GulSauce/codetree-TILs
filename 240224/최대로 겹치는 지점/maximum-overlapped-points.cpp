#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int board[101];

int main() {
    int n;
    cin >> n;
    while(n--){
        int x1, x2;
        cin >> x1 >> x2;
        for(int i = x1; i <= x2; i++){
            board[i]++;
        }
    }

    int maxSameLine = INT_MIN;
    for(int i = 1; i <= 100; i++){
        maxSameLine = max(maxSameLine, board[i]);
    }

    cout << maxSameLine;
    return 0;
}