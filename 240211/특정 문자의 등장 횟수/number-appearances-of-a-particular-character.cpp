#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    string target1 = "ee";
    int target1Cnt = 0;

    string target2 = "eb";
    int target2Cnt = 0;

    int lastIdx = str.length()-2;
    for(int i = 0; i <= lastIdx; i++){
        if(str.substr(i, 2) == target1){
            target1Cnt++;
        }
    }

    for(int i = 0; i < lastIdx; i++){
        if(str.substr(i, 2) == target2){
            target2Cnt++;
        }
    }

    cout << target1Cnt << ' ' << target2Cnt;
    return 0;
}