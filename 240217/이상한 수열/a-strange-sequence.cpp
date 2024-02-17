#include <iostream>

using namespace std;

int getNumber(int number){
    if(number == 1){
        return 1;
    }
    if(number == 2){
        return 2;
    }

    return getNumber(number / 3) + getNumber(number- 1);
}

int main() {
    int N;
    cin >> N;
    int number = getNumber(N);
    cout << number;
    return 0;
}