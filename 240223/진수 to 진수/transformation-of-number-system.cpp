#include <iostream>
#include <string>

using namespace std;

int calcDecimalValue(int numberValue, int expValue){
    if(expValue == 0){
        return 1;
    }

    int halfValue = calcDecimalValue(numberValue, expValue/2);
    int exp1 = 1;
    if(expValue % 2 == 1){
        exp1 = numberValue;
    }

    return halfValue * halfValue * exp1;
}

int getSomeBaseToDecimalValue(int base, string& value){
    int curDigit = value.length() - 1;

    int decimalValue = 0;
    for(int i = 0; value[i] != '\0'; i++){
        if(value[i] == '0'){
            curDigit--
            continue;
        }
        int charValueToNumberValue = value[i] - '0';
        decimalValue += charValueToNumberValue * calcDecimalValue(base, curDigit--);
    }

    return decimalValue;
}

int main() {
    int a, b;
    string n;
    cin >> a >> b >> n;

    int someBaseToDecimalValue = getSomeBaseToDecimalValue(a, n);

    cout << someBaseToDecimalValue << '\n';
    int digitArray[1000];
    int digitCnt = 0;

    while(true){
        if(someBaseToDecimalValue <= b-1){
            digitArray[digitCnt] = someBaseToDecimalValue;
            break;
        }
        digitArray[digitCnt++] = someBaseToDecimalValue % b;
        someBaseToDecimalValue /= b;
    }

    for(int i = digitCnt; i >= 0; i--){
        cout << digitArray[i];
    }

    return 0;
}