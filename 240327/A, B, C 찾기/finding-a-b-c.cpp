#include <iostream>
#include <algorithm>

using namespace std;

int sumArray[7];

int main() {
    for(int i = 0; i <= 6; i++){
        cin >> sumArray[i];
    }

    sort(sumArray, sumArray+7);

    int A = sumArray[0];
    int B = sumArray[1];
    int C = sumArray[6] - B - A;

    cout << A << ' ' << B << ' ' << C;
    return 0;
}