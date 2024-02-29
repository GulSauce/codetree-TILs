#include <iostream>
#include <climits>

using namespace std;

int landHeights[100];

int main() {
    int N, H, T;
    cin >> N >> H >> T;

    for(int i = 0; i <= N; i++){
        cin >> landHeights[i];
    }

    int minPrice = INT_MAX;
    for(int i = 0; i <= N-T; i++){
        int currentPrice = 0;
        for(int j = i; j <= i+T-1; j++){
            currentPrice += abs(landHeights[j] - H);
        }
        minPrice = min(minPrice, currentPrice);
    }

    cout << minPrice;
    return 0;
}