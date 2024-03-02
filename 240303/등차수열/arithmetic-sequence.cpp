#include <iostream>

using namespace std;

int n;

int numbers[100];

int getCurrentCount(int k){
    int count = 0;
    for(int i = 0; i <= n-1; i++){
        for(int j = i+1; j <= n-1; j++){
            if(k - numbers[i] == numbers[j] - k){
                count++;
            }
        }
    }
    return count;
}

int main() {
    cin >> n;
    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int maxCount = 0;
    for(int k = 0; k <= 100; k++){
        int currentCount = getCurrentCount(k);
        maxCount = max(currentCount, maxCount);
    }
    cout << maxCount;
    return 0;
}