#include <iostream>
#include <climits>

using namespace std;

int n;

int numbers[100];

int calcDiffSum(int doubleIndex, int removeIndex){
    numbers[doubleIndex] *= 2;

    int diffSum = 0;
    
    for(int i = 1; i <= n-1; i++){
        diffSum += abs(numbers[i] - numbers[i-1]);
    }

    if(removeIndex == 0){
        diffSum -= abs(numbers[1]-numbers[removeIndex]);
    }
    else if(removeIndex == n-1){
        diffSum -= abs(numbers[n-1]-numbers[n-2]);
    }

    numbers[doubleIndex] /= 2;

    return diffSum;
}

int main() {
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }
    
    int minDiffSum = INT_MAX;

    for(int doubleIndex = 0; doubleIndex <= n-1; doubleIndex++){
        for(int removeIndex = 0; removeIndex <= n-1; removeIndex++){
            minDiffSum = min(minDiffSum, calcDiffSum(doubleIndex, removeIndex));
        }
    }

    cout << minDiffSum;
    return 0;
}