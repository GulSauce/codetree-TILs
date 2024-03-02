#include <iostream>

using namespace std;

bool checkIsInterestingNumber(int number){
    int numberCount[10] = {};
    
    while(number){
        int currentDigit = number % 10;
        numberCount[currentDigit]++;
        number /= 10;
    }

    int oneCount = 0;
    int overOneCount = 0;
    for(int i = 0; i <= 9; i++){
        if(numberCount[i] == 1){
            oneCount++;
        }
        if(2 <= numberCount[i]){
            overOneCount++;
        }
    }
    if(overOneCount == 1 && oneCount == 1){
        return true;
    }
    return false;
}

int main() {
    int X, Y;
    cin >> X >> Y;

    int ans = 0;
    for(int i = X; i <= Y; i++){
        if(checkIsInterestingNumber(i)){
            ans++;
        }
    }

    cout << ans;
    return 0;
}