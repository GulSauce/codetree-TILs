#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    char firstCh = str[0];
    char secondCh = str[1];
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == secondCh){
            str[i] = firstCh;
        }
    }

    cout << str;
    return 0;
}