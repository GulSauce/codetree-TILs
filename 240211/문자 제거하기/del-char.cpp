#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    while(2 <= str.length()){
        int targetIdx; cin >> targetIdx;
        int lastIdx = str.length() - 1;
        if(lastIdx < targetIdx){
            targetIdx = lastIdx;
        }
        str.erase(targetIdx, 1);
        cout << str << '\n';
    }
    return 0;
}