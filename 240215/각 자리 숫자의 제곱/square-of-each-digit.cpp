#include <iostream>

using namespace std;

int getDigitSquare(int number){
    if(number == 0){
        return 0;
    }
    int digitNumber = number % 10;
    digitNumber *= digitNumber; 
    int sumValue = getDigitSquare(number / 10) + digitNumber;
    return sumValue;
}

int main() {
    int N;
    cin >> N;
    int sumValue = getDigitSquare(N);
    cout << sumValue;
    return 0;
}