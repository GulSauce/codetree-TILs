#include <iostream>

using namespace std;

int N;

int getSumValue(int number){
    if(N % 2 == 0 && number == 0){
        return 0;
    }
    if(N % 2 == 1 && number == -1){
        return 0;
    }
    return number + getSumValue(number - 2);
}

int main() {

    cin >> N;
    int sumValue = getSumValue(N);

    cout << sumValue;

    return 0;
}