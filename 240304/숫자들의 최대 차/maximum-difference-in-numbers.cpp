#include <iostream>

using namespace std;

int numbers[1000];

int main() {
    int N, K;
    cin >> N >> K;
    
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    int maxCount = 0;
    for(int i = 0; i <= N-1; i++){
        int minNum = numbers[i];
        int currentCount = 0;
        for(int j = 0; j <= N-1; j++){
            if(numbers[j] + 1 <= numbers[i]
            || numbers[i] + K + 1 <= numbers[j]){
                continue;
            }
            currentCount++;
        }
        maxCount = max(maxCount, currentCount);
    }

    cout << maxCount;
    return 0;
}