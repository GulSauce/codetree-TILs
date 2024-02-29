#include <iostream>

using namespace std;

int numbers[100];

int main() {
    int n, k;
    cin >> n >> k;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int maxSum = 0;
    for(int i = 0; i <= n-k; i++){
        int currentSum = 0;
        for(int j = i; j <= i+2; j++){
            currentSum += numbers[j];
        }
        maxSum = max(maxSum, currentSum);
    }

    cout << maxSum;
    return 0;
}