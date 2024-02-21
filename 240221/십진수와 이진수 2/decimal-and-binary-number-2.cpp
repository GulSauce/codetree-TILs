#include <iostream>
#include <string>

using namespace std;

int getExpValue(int number, int exp){
    if(exp == 0){
        return 1;
    }    

    int halfValue = getExpValue(number, exp/2);

    int exp1Value = 1;

    if(exp % 2 == 1){
        exp1Value = number;
    }

    return halfValue * halfValue * exp1Value;
}

int main() {
    string str;
    cin >> str;

    int decimalValue = 0;
    int currentExp = str.length() - 1;
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == '0'){
            currentExp--;
            continue;
        }
        decimalValue += getExpValue(2, currentExp--);
    }
    decimalValue *= 17;

    int decimalToBinary[1000];
    int digitCnt = 0;

    while(decimalValue){
        if(decimalValue <= 1){
            decimalToBinary[digitCnt++] = decimalValue;
            break;
        }
        decimalToBinary[digitCnt++] = decimalValue % 2;
        decimalValue /= 2;
    }

    for(int i = digitCnt-1; i >= 0; i--){
        cout << decimalToBinary[i];
    }
    return 0;
}