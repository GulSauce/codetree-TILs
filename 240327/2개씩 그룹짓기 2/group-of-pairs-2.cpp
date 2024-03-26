#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int numbers[200000];

int main() {
    int n;
    cin >> n;
    for(int i = 0; i <= 2*n-1; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers+2*n);

    int maxMinDiff = INT_MAX;

    for(int i = 0; i <= n-1; i++){
        maxMinDiff = min(numbers[i+n] - numbers[i], maxMinDiff);
    }

    cout << maxMinDiff;
    return 0;
}