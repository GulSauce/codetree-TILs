#include <iostream>

using namespace std;

int main() {
    double tempScore;
    double scoreSum = 0;
    for(int i = 0; i < 8; i++){
        cin >> tempScore;
        scoreSum += tempScore;
    }
    cout << fixed;
    cout.precision(1);

    cout << scoreSum / 8 << '\n';
    return 0;
}