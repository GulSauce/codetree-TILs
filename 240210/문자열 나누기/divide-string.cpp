#include <iostream>
#include <string>

using namespace std;

int main() {
    int n; cin >> n;
    string cocatedStr = "";
    while(n--){
        string str; cin >> str;
        cocatedStr += str;
    }

    for(int i = 0; cocatedStr[i] != '\0'; i++){
        if(i % 5 == 0 && i != 0){
            cout << '\n';
        }
        cout << cocatedStr[i];
    }
    return 0;
}