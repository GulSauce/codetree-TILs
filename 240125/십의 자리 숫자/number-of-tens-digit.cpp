#include <iostream>

using namespace std;

int getTenDigit(int num){
    return num / 10 % 10;
}

int main() {
    int tenDigitCount[10] = {};

    while(1){
        int num; cin >> num;
        if(num == 0){
            break;
        }
        int tenDigit = getTenDigit(num);
        tenDigitCount[tenDigit]++;
    }

    for(int i = 1; i <= 9; i++){
        cout << i << " - " << tenDigitCount[i] << '\n';
    }

    return 0;
}