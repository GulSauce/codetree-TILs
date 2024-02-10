#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int lastIndex = str.length() - 1;
    for(int i = lastIndex; i >= 0; i--){
        if((i+1) % 2 == 0){
            cout << str[i];
        }
    }
    return 0;
}