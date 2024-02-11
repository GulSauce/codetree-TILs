#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    char target; cin >> target;
    int searchedIdx = str.find(target);
    if(searchedIdx == string::npos){
        cout << "No";
    }
    else{
        cout << searchedIdx;
    }
    return 0;
}