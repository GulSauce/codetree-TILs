#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    string targetStr; cin >> targetStr;
    int searchedIdx = str.find(targetStr);
    if(searchedIdx == string::npos){
        cout << -1;
    }
    else{
        cout << searchedIdx;
    }
    return 0;
}