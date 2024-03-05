#include <iostream>
#include <climits>

using namespace std;

int numbers[100];

int main() {
    int n, k;
    cin >> n >> k;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int minCost = INT_MAX;

    for(int minNumber = 1; minNumber <= 10000; minNumber++){
        int cost = 0;
        for(int i = 0; i <= n-1; i++){
            if(numbers[i] + 1 <= minNumber){
                cost += minNumber - numbers[i];
            }
            if(minNumber + k + 1 <= numbers[i]){
                cost += numbers[i] - minNumber - k;
            }
        }
        minCost = min(minCost, cost);
    }

    cout << minCost;
    return 0;
}