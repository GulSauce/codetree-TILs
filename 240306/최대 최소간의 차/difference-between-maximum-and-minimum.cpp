#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int numbers[100];

int main() {
    int n, k;
    cin >> n >> k;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers+n);

    int minCost = INT_MAX;

    for(int i = 0; i <= n-1; i++){
        for(int j = i; j <= n-1; j++){
            int minNumber = numbers[i];
            int maxNumber = numbers[j];
            if(k + 1 <= maxNumber - minNumber){
                continue;
            }
            int cost = 0;
            for(int i = 0; i <= n-1; i++){
                if(numbers[i] + 1 <= minNumber){
                    cost += minNumber - numbers[i];
                }
                if(maxNumber + 1 <= numbers[i]){
                    cost += numbers[i] - maxNumber;
                }
            }
            minCost = min(minCost, cost);
        }
    }

    cout << minCost;
    return 0;
}