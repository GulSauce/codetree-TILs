#include <climits>
#include <iostream>
#include <algorithm>

using namespace std;

int houses[101];

int main() {
    int n;
    cin >> n;

    for(int i = 1; i <= n; i++){
        cin >> houses[i];
    }

    int lengthSum = INT_MAX;
    for(int currentX = 1; currentX <= n; currentX++){
        int currentLengthSum = 0;
        for(int otherX = 1; otherX <= n; otherX++){
            currentLengthSum += houses[otherX]*abs(currentX - otherX);
        }
        lengthSum = min(lengthSum, currentLengthSum);
    }

    cout << lengthSum;
    return 0;
}