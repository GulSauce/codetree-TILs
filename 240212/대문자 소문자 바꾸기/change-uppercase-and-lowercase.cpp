#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    string str; cin >> str;
    for(int i = 0; str[i]; i++){
        char curCh = str[i];
        if('a' <= curCh && curCh <= 'z'){
            cout << (char) toupper(curCh);
        }
        else if('A' <= curCh && curCh <= 'Z'){
            cout << (char) tolower(curCh);
        }
    }
    return 0;
}