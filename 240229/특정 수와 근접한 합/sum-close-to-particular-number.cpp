#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int N, S;
int sum;

int numbers[101];

int getCurrentSum(int first, int second){
    int dupSum = sum;
    dupSum -= numbers[first];
    dupSum -= numbers[second];

    return dupSum;
}

int main() {
    cin >> N >> S;

    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
        sum += numbers[i];
    }

    int minDiff = INT_MAX;

    for(int firstNumberIndex = 0; firstNumberIndex <= N-1; firstNumberIndex++){
        for(int secondNumberIndex = firstNumberIndex+1; secondNumberIndex <= N-1; secondNumberIndex++){
            int currentSum = getCurrentSum(firstNumberIndex, secondNumberIndex);
            minDiff = min(minDiff, abs(S - currentSum));
        }
    }

    cout << minDiff;
    return 0;
}