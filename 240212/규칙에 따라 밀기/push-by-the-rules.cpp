#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    cin >> str;
    string command;
    cin >> command;

    int length = str.length();

    for(int i = 0; command[i] != '\0'; i++){
        char curCommand = command[i];

        if(curCommand == 'L'){
            str = str.substr(1, length - 1) + str.substr(0, 1);
        }
        else if(curCommand == 'R'){
            str = str.substr(length - 1, 1) + str.substr(0, length - 1);
        }
    }
    cout << str;
    return 0;
}