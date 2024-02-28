#include <iostream>
#include <string>

using namespace std;

int power(int number, int exp){
    if(exp == 0){
        return 1;
    }
    
    int halfValue = power(number, exp/2);
    int exp1 = 1;
    if(exp % 2 == 1){
        exp1 = number;
    }
    return halfValue * halfValue * exp1;
}

int binaryToDecimal(string& binaryNumbers){
    int exp = binaryNumbers.length();
    int value = 0;
    for(char binaryNumber : binaryNumbers){
        exp--;
        if(binaryNumber == '0'){
            continue;
        }
        value += power(2, exp);
    }
    return value;
}

int main() {
    string binaryNumbers;
    cin >> binaryNumbers;

    bool isSearched = false;
    int result = 0;
    for(int i = 0; binaryNumbers[i] != '\0'; i++){
        int currentValue = 0;
        binaryNumbers[i] = '0' + '1' - binaryNumbers[i];

        currentValue = binaryToDecimal(binaryNumbers);
        result = max(result, currentValue);
        
        binaryNumbers[i] = '0' + '1' - binaryNumbers[i];
    }
    
    cout << result;
    return 0;
}