#include <iostream>
#include <string>

using namespace std;

int main() {
    while(true){
        string str; cin >> str;
        if(str == "END"){
            break;
        }

        int length = str.length();
        for(int i = length-1; i >= 0; i--){
            cout << str[i];
        }

        cout << '\n';
    }
    return 0;
}