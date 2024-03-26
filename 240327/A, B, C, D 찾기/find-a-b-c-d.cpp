#include <iostream>
#include <algorithm>

using namespace std;

int sumArray[15];

int main() {
    for(int i = 0; i <= 14; i++){
        cin >> sumArray[i];
    }

    sort(sumArray, sumArray + 15);

    int A = sumArray[0];
    int B = sumArray[1];
    int D = sumArray[11] - A - B;
    int C = sumArray[14] - A - B - D;

    cout << A << ' ' << B << ' ' << C << ' ' << D;
    return 0;
}