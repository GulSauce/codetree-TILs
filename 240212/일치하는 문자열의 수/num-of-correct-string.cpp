#include <iostream>
#include <string>

using namespace std;

int main() {
    int n;
    cin >> n;

    string A;
    cin >> A;

    int matchCnt = 0;
    while(n--){
        string str;
        cin >> str;
        if(A == str){
            matchCnt++;
        }
    }

    cout << matchCnt;
    return 0;
}