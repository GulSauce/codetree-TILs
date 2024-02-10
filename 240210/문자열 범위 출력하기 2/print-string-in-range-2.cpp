#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int iterCnt; cin >> iterCnt;
    int lastIndex = str.length()-1;
    int curInterCnt = 0;
    for(int i = lastIndex; i >= 0; i--){
        if(++curInterCnt > iterCnt){
            break;
        }
        cout << str[i];
    }

    return 0;
}