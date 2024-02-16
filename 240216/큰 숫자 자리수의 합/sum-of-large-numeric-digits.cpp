#include <iostream>

using namespace std;

int getDigitSumValue(int value){
    if(value == 0){
        return 0;
    }
    int curDigitSumValue = value % 10 + getDigitSumValue(value / 10);
    return curDigitSumValue;
}

int main() {
    int n1, n2, n3;
    cin >> n1 >> n2>> n3;
    int digitSumValue = getDigitSumValue(n1*n2*n3);
    cout << digitSumValue;
    return 0;
}