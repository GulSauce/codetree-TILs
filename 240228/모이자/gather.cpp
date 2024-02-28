#include <climits>
#include <iostream>
#include <algorithm>

using namespace std;

int houses[100];

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        cin >> houses[i];
    }

    int lengthSum = INT_MAX;
    for(int target = 0; target <= n-1; target++){
        int currentLengthSum = 0;
        for(int otherHouse = 0; otherHouse <= n-1; otherHouse++){
            cout << abs(houses[target] - houses[otherHouse]); << '\n';
            currentLengthSum += abs(houses[target] - houses[otherHouse]);
        }
        cout << '\n';
        lengthSum = min(lengthSum, currentLengthSum);
    }

    cout << lengthSum;
    return 0;
}