#include <iostream>
#include <string>

using namespace std;

bool isPalindrome(int number){
    string numberString = to_string(number);
    int lastIndex = numberString.length() - 1;
    int halfIndex = (lastIndex-1) / 2;
    for(int i = 0; i <= halfIndex; i++){
        if(numberString[i] != numberString[lastIndex-i]){
            return false;
        }
    }
    return true;
}

int main() {
    int X, Y;
    cin >> X >> Y;

    int count = 0;
    for(int i = X; i <= Y; i++){
        if(isPalindrome(i)){
            count++;
        }
    }

    cout << count;
    return 0;
}