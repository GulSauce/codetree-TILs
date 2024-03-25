#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int numbers[100000];

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers + n);

    int maxNumber = -1 * INT_MAX;

    maxNumber = max(maxNumber, numbers[n-1] * numbers[n-2] * numbers[n-3]);
    maxNumber = max(maxNumber, numbers[n-1] * numbers[n-2] * numbers[0]);
    maxNumber = max(maxNumber, numbers[n-1] * numbers[0] * numbers[1]);
    maxNumber = max(maxNumber, numbers[0] * numbers[1] * numbers[2]);

    cout << maxNumber;
    return 0;
}