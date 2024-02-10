#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    if(str.find("ee") != string::npos){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    cout << ' ';
    if(str.find("ab") != string::npos){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    return 0;
}