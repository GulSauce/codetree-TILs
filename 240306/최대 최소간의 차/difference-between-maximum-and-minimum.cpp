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

    int maxNumber = numbers[n-1];
    
    int minCost = INT_MAX;
    for(int currentMinNumber = numbers[0]; currentMinNumber <= maxNumber; currentMinNumber++){
        for(int currentMaxNumber = currentMinNumber; currentMaxNumber <= maxNumber; currentMaxNumber++){
            if(k + 1 <= currentMaxNumber - currentMinNumber){
                continue;
            }
            int cost = 0;
            for(int i = 0; i <= n-1; i++){
                if(numbers[i] + 1 <= currentMinNumber){
                    cost += currentMinNumber - numbers[i];
                }
                if(currentMaxNumber + 1 <= numbers[i]){
                    cost += numbers[i] - currentMaxNumber;
                }
            }
            minCost = min(minCost, cost);
        }
    }

    cout << minCost;
    return 0;
}