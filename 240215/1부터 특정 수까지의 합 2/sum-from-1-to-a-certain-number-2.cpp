#include <iostream>

using namespace std;

int N;

int sumNto1(int number){
    if(number == 0){
        return 0;
    }

    int sumValue = sumNto1(number -1) + number;

    return sumValue;
}

int main() {
    cin >> N;
    int sumValue = sumNto1(N);

    cout << sumValue;
    return 0;
}