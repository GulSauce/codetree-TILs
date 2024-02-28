#include <iostream>

using namespace std;

int numbers[101];

int main() {
    int n; cin >> n;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int maxValue = 0;
    for(int i = 0; i <= n-1; i++){
        for(int j = i + 2; j <= n-1; j++){
            int currentValue = numbers[i] + numbers[j];
            maxValue = max(maxValue, currentValue);
        }
    }

    cout << maxValue;
    return 0;
}