#include <iostream>
#include <string>

using namespace std;

int main() {
    string str[10];
    for(int i = 0; i <= 9; i++){
        cin >> str[i];
    }
    
    char ch; cin >> ch;
    bool isExist = false;

    for(int i = 0; i <= 9; i++){
        int lastIndex = str[i].length() - 1;
        if(str[i][lastIndex] == ch){
            cout << str[i] << '\n';
            isExist = true;
        }
    }

    if(isExist == false){
        cout << "None";
    }
    return 0;
}