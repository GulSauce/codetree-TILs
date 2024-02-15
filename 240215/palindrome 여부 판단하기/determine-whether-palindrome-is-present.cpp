#include <iostream>
#include <string>

using namespace std;

bool checkIsPalindrome(string str){
    int finishIndex = str.length() / 2 - 1;
    int lastIndex = str.length() - 1;
    for(int i = 0; i <= finishIndex; i++){
        if(str[i] != str[lastIndex-i]){
            return false;
        }
    }
    return true;
}

int main() {
    string str; cin >> str;
    bool isPalindrome = checkIsPalindrome(str);
    if(isPalindrome){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    return 0;
}