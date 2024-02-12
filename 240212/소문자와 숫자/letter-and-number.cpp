#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    string str; cin >> str;
    for(int i = 0; str[i]; i++){
        char curCh = str[i];
        if(isalpha(curCh) == 0 && isdigit(curCh) == 0){
            continue;
        }
        if(isdigit(curCh)){
            cout << curCh;
            continue;
        }
        cout << (char)tolower(curCh);
    }
    return 0;
}