#include <iostream>

using namespace std;

int main() {
    string str;
    cin >> str;
    int lastIndex = str.length() - 1;

    int ans = 0;
    for(int openParen = 0; openParen <= lastIndex-1; openParen++){
        for(int closeParen = openParen+2; closeParen <= lastIndex-1; closeParen++){
            if(str[openParen] == '(' && str[openParen+1] == '('
            && str[closeParen] == ')' && str[closeParen+1] == ')'){
                ans++;
            }
        }
    }

    cout << ans;
    return 0;
}