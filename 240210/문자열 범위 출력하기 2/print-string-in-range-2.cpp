#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int iterCnt; cin >> iterCnt;
    int curIndex = str.length()-1;
    while(iterCnt--){
        cout << str[curIndex--];
        if(curIndex <= -1){
            break;
        }
    }
    return 0;
}