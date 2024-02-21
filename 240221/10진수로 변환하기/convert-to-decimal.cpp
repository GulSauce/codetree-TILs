#include <iostream>
#include <string>

using namespace std;

int getValue(int number, int expValue){
    if(expValue == 0){
        return 1;
    }

    int halfValue = getValue(number, expValue/2);

    int exp1 = 1;

    if(expValue % 2 == 1){
        exp1 = number;
    }

    return halfValue*halfValue*exp1;
}

int getBinaryToDecimal(string& binaryStr){
    int expValue = binaryStr.length() - 1;
    int binaryToDecimal = 0;
    for(int i = 0; binaryStr[i] != '\0'; i++){
        if(binaryStr[i] == '0'){
            expValue--;
            continue;
        }
        binaryToDecimal += getValue(2, expValue--);
    }

    return binaryToDecimal;
}

int main() {
    string binaryStr; 
    cin >> binaryStr;

    int binaryToDecimal = getBinaryToDecimal(binaryStr);
    cout << binaryToDecimal;
    return 0;
}