#include <iostream>
#include <string>

using namespace std;

int main() {
    int strCnt = 0;
    string str[201];

    while(true){
        strCnt++;
        cin >> str[strCnt];
        if(str[strCnt] == "0"){
            break;
        }
    }

    strCnt--;

    cout << strCnt << '\n';
    for(int i = 1; i <= strCnt; i++){
        if(i % 2 == 1){
            cout << str[i] << '\n';
        }
    }
    return 0;
}