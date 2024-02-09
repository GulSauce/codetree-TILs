#include <iostream>
#include <string>

using namespace std;

int main() {
    string str1, str2;
    getline(cin, str1);
    getline(cin, str2);

    for(char ch : str1){
        if(ch == ' '){
            continue;
        }
        cout << ch;
    }

    for(char ch : str2){
        if(ch == ' '){
            continue;
        }
        cout << ch;
    }
    return 0;
}